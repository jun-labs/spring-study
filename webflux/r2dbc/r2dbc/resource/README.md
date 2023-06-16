## 연관관계 매핑 

R2DBC는 DDL을 지원하지 않으며 따라서 조인을 하기 위해서는 연관관계를 직접 맺어줘야 합니다. 

<br/><br/><br/><br/>

### @ManyToOne

하나의 게시글에 여러 개의 댓글이 있는 경우를 가정해 보겠습니다. 댓글 쪽에서는 게시글 객체를 가지고, 있어야 하는데요, 이를 위해서는 @Transient 어노테이션을 사용해 Post 객체를 필드로 넣어줍니다.    

```kotlin
@Table("post")
class Post(
    @Id
    @Column(value = "post_id")
    var postId: Long? = null,

    @Column(value = "user_id")
    var userId: Long,

    @Column(value = "club_id")
    var clubId: Long,

    @Column(value = "content")
    var content: String,

    @Column(value = "created_by")
    val createdBy: Long,

    @Column(value = "created_at")
    val createdAt: Instant,

    @Column(value = "last_modified_by")
    val lastModifiedBy: Long? = null,

    @Column(value = "last_modified_at")
    val lastModifiedAt: Instant? = null,

    @Column(value = "deleted")
    val deleted: Deleted
)
```

````kotlin
@Table(name = "comment")
class Comment(
    @Id
    @Column(value = "comment_id")
    val commentId: Long? = null,

    @Column(value = "user_id")
    val userId: Long,

    @Column(value = "post_id")
    val postId: Long,

    @Column(value = "content")
    val content: String,

    @Column(value = "created_by")
    val createdBy: Long,

    @Column(value = "created_at")
    val createdAt: Instant,

    @Column(value = "last_modified_by")
    val lastModifiedBy: Long? = null,

    @Column(value = "last_modified_at")
    val lastModifiedAt: Instant? = null,

    @Column(value = "deleted")
    val deleted: Deleted,

    @Transient
    val post: Post
)
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이후 아래와 같이 Converter를 이용해 댓글에서 게시글 객체를 사용할 수 있게 해줍니다.

```kotlin
@ReadingConverter
class PostReadConverter : Converter<Row, Comment> {

    override fun convert(source: Row): Comment? {
        val post = Post(
            postId = source["post_id"]?.let { it as Long },
            userId = source["user_id"] as Long,
            clubId = source["club_id"] as Long,
            content = source["content"] as String,
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String)
        )
        return Comment(
            commentId = source["comment_id"]?.let { it as Long },
            userId = source["user_id"] as Long,
            postId = source["post_id"] as Long,
            content = source["content"] as String,
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String),
            post = post    // 객체 지정
        )
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

설정을 마치면 아래와 같이 레포지토리에서 조인을 사용할 수 있습니다.

````kotlin
interface CommentQueryRepository : ReactiveCrudRepository<Comment, Long> {

    @Query("SELECT * FROM comment AS c JOIN post p on c.post_id = p.post_id WHERE c.post_id = :postId")
    fun findCommentById(postId: Long): Mono<Comment>?
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

## ArgumentResolver

본인이 등록하고자 하는 ArgumentResolver를 생성합니다.

```kotlin
@Component
class LoginArgumentResolver(
    private val userRepository: UserQueryRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> {
        val userId = exchange.request.headers.getFirst("authentication")

        // TODO. runBlocking 수정
        val findUser = runBlocking { findUserById(parseLong(userId)) }
        return Mono.fromCallable { MoimUser(findUser) }
    }

    fun parseLong(userId: String?): Long {
        if (userId != null) {
            return userId.toLong()
        }
        throw MoimException(UnAuthorizedException())
    }

    suspend fun findUserById(userId: Long): User {
        return userRepository.findUserById(1, Deleted.FALSE)?.awaitSingle()
            ?: fail(UserNotFoundException())
    }
}
```



<br/><br/><br/><br/><br/><br/><br/><br/>

WebFlux에서 ArgumentResolver를 등록하기 위해서는 `WebFluxConfigurer`를 구현상속 해줍니다. 이후 자신이 생성한 ArgumentResolver를 등록해 줍니다.

```kotlin
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
class WebConfiguration(
    private val userQueryRepository: UserQueryRepository
) : WebFluxConfigurer {

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(LoginArgumentResolver(userQueryRepository))
    }
}
```

<br/>

