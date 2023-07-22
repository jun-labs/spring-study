## UPSERT

JPA에서 `ùpsert` 쿼리를 어떻게 사용하는지에 대해 살펴보겠습니다. 해시태그를 저장하는 예제인데요, 엔티티는 아래와 같습니다.

```kotlin
@Entity(name = "hashtag")
class HashtagJpaEntity(
    @Id
    @Column(name = "hashtag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val hashtagId: Long? = null,

    @Column(name = "tag_name")
    private var _tagName: String,

    @Column(name = "created_by")
    val createdBy: Long,
) : BaseEntity() {
    @Column(name = "last_modified_by")
    private var _lastModifiedBy: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')", name = "deleted")
    private var _deleted: Deleted? = Deleted.FALSE

    private constructor(
        hashtagId: Long?,
        tagName: String,
        createdBy: Long,
        lastModifiedBy: Long? = null,
        deleted: Deleted
    ) : this(
        hashtagId,
        tagName,
        createdBy
    ) {
        this._lastModifiedBy = lastModifiedBy
        this._deleted = deleted
    }

    val tagName: String
        get() = _tagName

    val lastModifiedBy: Long?
        get() = _lastModifiedBy

    val deleted: Deleted
        get() = _deleted!!

    override fun toString(): String {
        return hashtagId.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HashtagJpaEntity) return false
        if (hashtagId != other.hashtagId) return false
        return true
    }

    override fun hashCode(): Int {
        return hashtagId?.hashCode() ?: 0
    }

    companion object {
        fun of(
            hashtagId: Long?,
            tagName: String,
            createdBy: Long,
            lastModifiedBy: Long? = null,
            deleted: Deleted
        ): HashtagJpaEntity {
            return HashtagJpaEntity(
                hashtagId,
                tagName,
                createdBy,
                lastModifiedBy,
                deleted
            )
        }
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

JPA 레포지토리에서는 아래와 같이 @Query 어노테이션을 이용해 직접 값들을 바인딩 해주면 됩니다.

```kotlin
interface HashtagQueryRepository : JpaRepository<HashtagJpaEntity, Long> {

    // 값을 직접 바인딩 
    @Query(
        value = "INSERT INTO hashtag VALUES(" +
                ":hashtagId,:createdAt, :lastModifiedAt, :deleted, :lastModifiedBy, :tagName, :createdBy" +
                ") ON DUPLICATE KEY UPDATE tag_name = :tagName",
        nativeQuery = true
    )
    fun upsert(
        hashtagId: Long?,
        tagName: String,
        createdBy: Long?,
        createdAt: Instant?,
        lastModifiedBy: Long?,
        lastModifiedAt: Instant?,
        deleted: Deleted
    ): HashtagJpaEntity?
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

물론 이렇게 해주는 것보다 스키마 자체에 `UNIQUE`를 걸어서 이를 제어해주는 것이 더 좋습니다. 

```sql
CREATE TABLE hashtag
(
    hashtag_id       BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '해시태그 PK',
    tag_name         VARCHAR(30)                       NOT NULL UNIQUE KEY COMMENT '태그명',    // UNIQUE
    created_by       BIGINT                            NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)                       NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                            NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)                       NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE')            NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '해시태그';
```

> INSERT할 때 PK 값은 넣어주지 않아도 됩니다.
