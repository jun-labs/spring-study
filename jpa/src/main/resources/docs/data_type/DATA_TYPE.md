## 🚀 Data Type

JPA data type learning.


&nbsp;&nbsp; [1. TINYINT](#TINYINT) <br/>
&nbsp;&nbsp; [2. DATETIME / DATE / TIMESTAMP](#날짜) <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - [2-1. @Temporal](#Temporal) <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - [2-2. @LastModifiedDate / @UpdateTimestamp](#수정일-업데이트) <br/>
&nbsp;&nbsp; [3. Enum / Set](#ENUM과-SET) <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - [3-1. ENUM](#ENUM) <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - [3-2. SET](#SET) <br/>
&nbsp;&nbsp; [4. ENVERS](#ENVERS) <br/>

<br/><br/><br/><br/>

## TINYINT

MySQL은 INTERE(또는 INT)와 SMALLINT SQL 표준 정수 타입을 지원합니다. 이 표준의 확장으로 MySQL은 [TINYINT](https://dev.mysql.com/doc/refman/8.0/en/integer-types.html), MEDIAINT, BIGINT 정수형도 지원합니다.

> MySQL supports the SQL standard integer types INTEGER (or INT) and SMALLINT. As an extension to the standard, MySQL
> also supports the integer types TINYINT, MEDIUMINT, and BIGINT. The following table shows the required storage and range
> for each integer type.

<br/><br/><br/><br/><br/><br/><br/><br/>

지원하는 데이터 타입과 범위는 아래 표와 같습니다.

![image](./images/mysql_document_data_type.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

이를 JPA로 매핑하기 위해서는 아래와 같이 `@Column(columnDefinition = "TINYINT")`라고 명시해 주면 됩니다.

````kotlin
@Entity
class Weather(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var weatherId: Long? = null,

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private val isTrue: Int
) {}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 Boolean 형태로 저장되기도 하는데요, 아래와 같이 매핑하더라도 오류가 나지 않습니다.

````kotlin
@Entity
class Weather(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var weatherId: Long? = null,

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private val isTrue: Int,

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private val isToday: Boolean
) {}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

실제 데이터 베이스에 생성된 타입을 보면 BOOLEAN이 아닌 TINYINT로 칼럼이 생성된 것을 볼 수 있습니다.

```sql
CREATE TABLE weather
(
    weather_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    is_today   TINYINT NOT NULL,
    is_true    TINYINT NOT NULL
);
```

<br/><br/><br/><br/><br/><br/><br/><br/>

데이터를 조회할 때 아래와 같이 `TRUE/FALSE` 또는 `0/1`을 사용하면 됩니다.

````sql
SELECT *
FROM weather
WHERE is_today = TRUE
````

````sql
SELECT *
FROM weather
WHERE is_today = 1
````

<br/><br/><br/><br/><br/><br/><br/><br/>

단 이를 QueryDsl을 사용할 때는 정확히 일치하는 타입을 넣어줘야 합니다.

````kotlin
@Repository
class WeatherQueryDslRepository(private val queryFactory: JPAQueryFactory) {

    fun findWeatherByIdWithIsToday(weatherId: Long): Weather {
        return queryFactory.selectFrom(weather)
            .where(weather.isToday.eq(true))    // 정확한 타입으로 비교
            .fetchOne()!!
    }

    fun findWeatherByIdWithIsTrue(weatherId: Long): Weather {
        return queryFactory.selectFrom(weather)
            .where(weather.isTrue.eq(1))        // 정확한 타입으로 비교
            .fetchOne()!!
    }
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

오류 메시지를 보면 아래와 같이 정확한 타입을 넣으라는 것을 볼 수 있습니다. 

![image](./images/querydsl_compile_error2.png)

![image](./images/querydsl_compile_error1.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

## 날짜

JPA에서 날짜에 관해 어떻게 매핑하고 처리하는지 살펴보겠습니다. 

<br/><br/><br/><br/>

### Temporal

@Temporal 어노테이션을 사용해 예약(Reservation) 테이블을 아래와 같이 매핑해줍니다.

````kotlin
@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var reservationId: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", updatable = false)
    private val createdAt: Instant? = Instant.now(),

    @Temporal(TemporalType.TIME)
    @Column(name = "firstModifiedAt")
    private val firstModifiedAt: Date? = Date(),

    @Temporal(TemporalType.DATE)
    @Column(name = "secondModifiedAt")
    private val secondModifiedAt: Instant? = Instant.now(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thirdModifiedAt", columnDefinition = "TIMESTAMP")
    private val thirdModifiedAt: Instant? = Instant.now(),

    @Temporal(TemporalType.DATE)
    @Column(name = "lastModifiedAt")
    private val lastModifiedAt: Date? = Date()
) {
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

생성된 테이블을 보면 아래와 같습니다. 이는 자바의 시간 관련 클래스 외에도 `@Temporal`의 값에 따라 데이터베이스의 칼럼 값들이 달라지는 것을 볼 수 있는데 `@Temporal(TemporalType.TIMESTAMP)는 DATETIME`에, `@Temporal(TemporalType.TIME)는 TIME`에, `@Temporal(TemporalType.DATE)는 DATE`에 매핑 됩니다.

````sql
CREATE TABLE reservation
(
    reservation_id     BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at         DATETIME(6) NULL,
    first_modified_at  TIME        NULL,
    fourth_modified_at DATETIME(6) NULL,
    last_modified_at   DATE        NULL,
    second_modified_at DATE        NULL,
    third_modified_at  TIMESTAMP   NULL
) engine = InnoDB;
````

<br/><br/><br/><br/><br/><br/><br/><br/>

여기서 살펴볼 점이 있는데요, createdAt과 fourthModifiedAt 입니다. 둘 다 @Temporal(TemporalType.TIMESTAMP)로 매핑했지만 타입은 다릅니다. createdAt은 Instant, fourthModifiedAt은 LocalDateTime입니다.  

````kotlin
@Entity
class Reservation(
    
    ......
            
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", updatable = false)
    private val createdAt: Instant? = Instant.now(),

    ......
            
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fourthModifiedAt", updatable = false)
    private val fourthModifiedAt: LocalDateTime? = LocalDateTime.now(),
)
````

<br/><br/><br/><br/><br/><br/><br/><br/>

하지만 생성된 테이블을 보면 같은 타입의 칼럼인 것을 볼 수 있습니다. 이때 `어떤 것을 쓰는 게 더 좋을지`에 대한 고민이 들었는데 이에 대한 결론은 `운영 중인 서비스의 성격에 맞게 사용하는 것이 정답이다.`라는 것이었습니다. 즉 국내 서비스만을 위해서라면 LocalDate 타입도 나쁘지 않습니다. 반면 글로벌 서비스를 운영 중이라면 타임 존 이나 기타 여러 가지 사항을 고려해야 하기 때문에 Instant가 더 적절해 보였습니다. 따라서 자신이 운영 중인 서비스에 맞게 적절한 타입을 사용하면 될 것 같습니다.

````sql
CREATE TABLE reservation
(
    reservation_id     BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at         DATETIME(6) NULL,
    
    ......
    
    fourth_modified_at DATETIME(6) NULL
) engine = InnoDB;
````

<br/><br/><br/><br/><br/><br/><br/><br/>

### 수정일 업데이트
@LastModifiedDate와 @UpdateTimestamp는 둘 다 JPA 엔티티에서 사용되는 어노테이션으로 `엔티티의 마지막 수정 날짜 및 시간을 추적`하는 데 사용됩니다. 그러나 두 어노테이션은 서로 다른 라이브러리에서 제공되며 약간의 차이가 있습니다.

<br/><br/><br/><br/>

@LastModifiedDate는 Spring Data JPA 라이브러리에서 제공됩니다. 이는 엔티티가 마지막으로 수정된 날짜 및 시간을 추적합니다. 해당 필드를 업데이트할 때마다 해당 시간이 자동으로 갱신되며 일반적으로 스프링 데이터 JPA의 AuditingEntityListener와 함께 사용되어 엔티티의 변경 이벤트에 응답하고 수정 시간을 업데이트합니다.

```kotlin
// Data JPA 라이브러리
package org.springframework.data.annotation;

......

/**
 * Declares a field as the one representing the date the entity containing the field was recently modified.
 *
 * @author Ranie Jade Ramiso
 * @author Oliver Gierke
 * @since 1.5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD, METHOD, ANNOTATION_TYPE })
public @interface LastModifiedDate {
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

Spring Data JPA 패키지를 살펴보면 @LastModifiedDate 어노테이션이 있는 것을 볼 수 있습니다.  

![image](./images/lastmodifiedate.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

@UpdateTimestamp는 Hibernate ORM 라이브러리에서 제공됩니다. 이 또한  엔티티가 마지막으로 수정된 날짜 및 시간을 추적합니다. 해당 필드를 업데이트할 때마다 해당 시간이 자동으로 갱신되며, Hibernate에서 제공하는 내장 기능으로 엔티티를 저장하거나 업데이트할 때 자동으로 설정됩니다.

````kotlin
package org.hibernate.annotations;

......

@ValueGenerationType(generatedBy = UpdateTimestampGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD })
public @interface UpdateTimestamp {
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

Hibernate 패키지를 살펴보면 @LastModifiedDate 어노테이션이 있는 것을 볼 수 있습니다.  

![image](./images/updatetimestamp.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

이에 대해 실습을 진행해보겠습니다. 아래와 같이 도시(City) 테이블을 생성하고 엔티티를 매핑해줍니다. 

```sql
CREATE TABLE city
(
    city_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(15) NULL,
    last_modified_at DATETIME    NULL
);
```

````kotlin
@Entity
class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cityId: Long? = null,

    @Column
    private var name: String,

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private val lastModifiedAt: Instant
) {
    fun getCityId(): Long? {
        return cityId
    }

    fun getName(): String {
        return name
    }

    fun updateName(name: String) {
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is City) return false
        if (cityId != other.cityId) return false
        return true
    }

    override fun hashCode(): Int {
        return cityId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return cityId.toString()
    }
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

현재 데이터베이스의 상태는 아래와 같습니다.

![image](./images/updated_test.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

이후 도시의 이름을 업데이트 합니다.

```kotlin
@RestController
@RequestMapping("/api/cities")
class CityCommandAPI(private val cityCommandService: CityCommandService) {

    @PutMapping("/{cityId}")
    fun updateCityInformation(
        @PathVariable cityId: Long,
        @RequestBody request: CityUpdateRequest
    ): ResponseEntity<Unit> {
        cityCommandService.updateCity(cityId, request.name)
        return ResponseEntity.ok()
            .build()
    }
}
```
```kotlin
@Service
class CityCommandService(private val cityJpaRepository: CityJpaRepository) {

    @Transactional
    fun updateCity(
        cityId: Long,
        name: String
    ) {
        val findCity = cityJpaRepository.findById(cityId).get()
        findCity.updateName(name)
    }
}
```
<br/><br/><br/><br/><br/><br/><br/><br/>

업데이트를 한 후 데이터베이스를 살펴보면 아래와 같이 lastModifiedAt이 반영된 것을 볼 수 있습니다.

![image](./images/last_modified_attest_result.png)

```http request
### City Update API

PUT localhost:8098/api/cities/1
Content-Type: application/json

{
  "name": "SEOUL1"
}
```
<br/><br/><br/><br/><br/><br/><br/><br/>

정리해 보면 `@LastModifiedDate`는 `Spring Data JPA와 함께 사용되는 스프링 기반의 어노테이션`이며, `@UpdateTimestamp`는 `Hibernate ORM과 함께 사용되는 Hibernate 기반의 어노테이션`입니다. 둘 다 엔티티의 마지막 수정 날짜를 추적하고 자동으로 갱신되지만 사용하는 ORM 라이브러리에 따라 선택할 수 있습니다.


<br/><br/><br/><br/><br/><br/><br/><br/>

## ENVERS

Envers는 `JPA를 사용해 데이터베이스에서 변경 이력을 추적하고 조회하는 모듈`입니다. Envers는 객체 지향적 방식으로 엔티티의 상태 변화를 기록해 이력 정보를 쉽게 검색하고 복원할 수 있도록 도와줍니다. 

> [Envers is a Hibernate module that adds auditing capabilities to JPA entities. This documentation assumes you are familiar with Envers, just as Spring Data Envers relies on Envers being properly configured.](https://docs.spring.io/spring-data/envers/docs/current/reference/html/)

<br/><br/><br/><br/>

먼저 의존성을 추가합니다. 

````groovy
implementation("org.springframework.data:spring-data-envers:3.0.6")
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이를 사용하기 위해서는 스프링 부트 main 메서드에 `@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)`와 엔티티에 `@Audited`를 추가해 줍니다. 

```kotlin
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
class JpaApplication

fun main(args: Array<String>) {
    runApplication<JpaApplication>(*args)
}
```

````kotlin
@Audited    // 추가 
@Entity(name = "user")
@EntityListeners(value = [AuditingEntityListener::class])    
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var userId: Long? = null

    @Column
    private var nickname: String? = null

    @Column
    private var weight: Double? = null

    @Column
    private var phoneNumber: String? = null

    @Audited
    @Embedded
    private var auditedColumns: AuditedColumns = AuditedColumns(
        userId,
        Instant.now(),
        userId,
        null
    )

    ......
    
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

단일 데이터베이스를 사용할 때 아래의 설정만으로 동작하지만, 멀티 데이터 소스를 사용할 때는 별도의 설정이 필요합니다.

````kotlin
@Configuration
@EnableJpaRepositories(
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class,
        entityManagerFactoryRef = "${ENTITY_MANAGER_FACTORY_REF}",
        transactionManagerRef = "${TRANSACTION_MANAGER_REF}",
        basePackages = {
                            
        })
class PrimaryDatabaseConfiguration {
    
    ......
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이후 변경된 값들의 기록이 저장되는 REVINFO(revision_tracking) 테이블도 매핑해줍니다. 별도로 매핑해주지 않아도 테이블이 생성되지만, 이를 매핑하는 이유는 조금 있다 살펴보겠습니다.

````kotlin
@RevisionEntity
@Entity(name = "revision_tracking")
class RevisionTracker(
    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var rev: Long? = null,

    @RevisionTimestamp
    private var createdAt: Long
) : Serializable {

    ......
    
}
````

````kotlin
interface CommonRepository : JpaRepository<RevisionTracker, Long>, RevisionRepository<RevisionTracker, Long, Long> {
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이를 실행하면 아래와 같은 테이블이 생성되게 됩니다. 

```sql
CREATE TABLE revision_tracking
(
    rev        BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL
);
```

````sql
CREATE TABLE user_aud
(
    user_id      BIGINT                 NOT NULL PRIMARY KEY ,
    rev          BIGINT                 NOT NULL ,
    revtype      TINYINT                NULL ,
    created_at   DATETIME(6)            NULL ,
    created_by   BIGINT                 NULL,
    modified_by  BIGINT                 NULL,
    modified_at  DATETIME(6)            NULL,
    banned       ENUM ('TRUE', 'FALSE') NULL,
    nickname     VARCHAR(255)           NULL,
    phone_number VARCHAR(255)           NULL,
    weight       DOUBLE                 NULL,
    CONSTRAINT FK9f63gaxm79bxhgbglpn4kuht9 FOREIGN KEY (rev) REFERENCES revision_tracking (rev)
);
````

<br/><br/><br/><br/><br/><br/><br/><br/>

REVINFO 테이블은 revision_id와 timestamp 칼럼을 가지고 있으며 이력 테이블(user_aud)은 별도로 존재합니다. 이는 `동일 트랜잭션` 내에서 발생한 변경사항을 revision_id로 여러 이력 테이블에 각각 저장해 해당 트랜잭션에서 변경된 모든 이력을 추적/감시합니다. 트랜잭션 단위로 revision을 관리하기 때문에 한 트랜잭션에서 변경된 내용을 한 번에 파악할 수 있어 매우 편리합니다.

> 위에서 `REVINFO` 테이블을 왜 별도로 매핑하는지에 대해 의문이 들 수 있는데요, 이는 기본적으로 REVINFO 테이블의 PK가 INT이기 때문입니다. INT는 변경 사항이 많이 쌓이게 되면 금방 최대치를 초과할 수 있으므로 Long으로 바꾸는 것이 좋습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

또한 yml 설정을 통해 `동일 트랜잭션에서 함께 변경된 엔티티를 한 번에 조회`할 수도 있습니다. 

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: ${DDL_AUTO}
    generate-ddl: ${GENERATED_DDL}
    show-sql: true
    properties:
      org:
        hibernate:
          envers:
            track_entities_changed_in_revision: true               # 같은 트랜잭션에서 함께 변경된 엔티티를 검색  
            audit_table_suffix: ${AUDIT_TABLE_SUFFIX}              # audit 테이블 suffix 
            revision_field_name: ${REVISION_FIELD_NAME}            # revision 필드명 
            revision_type_field_name: ${REVISION_TYPE_FIELD_NAME}  # revision 타입 필드명
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이제 기본적인 이해가 끝났으니 사용자 정보를 업데이트해서 테이블을 살펴보겠습니다.

```kotlin
@RestController
@RequestMapping("/api/users")
class UserUpdateAPI(
    private val userUpdateService: UserUpdateService
) {

    @PutMapping("/{userId}")
    fun updateNickname(
        @PathVariable userId: Long,
        @RequestBody request: NicknameUpdateRequest
    ) {
        userUpdateService.updateNickname(
            userId,
            request.getNickname(),
            request.getWeight()
        )
    }
}
```

````kotlin
@Service
class UserUpdateService(private val userJpaRepository: UserJpaRepository) {

    @Transactional
    fun updateNickname(
        userId: Long,
        nickname: String,
        weight: Double
    ) {
        val findUser = userJpaRepository.findById(userId).orElseThrow();
        findUser.updateProfile(nickname, weight)
    }
}
````

````kotlin
interface UserJpaRepository : JpaRepository<User, Long> {
}
````

```http request
PUT localhost:8088/api/users/1
Content-Type: application/json

{
  "nickname": "HELLO!",
  "weight": 75
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이를 업데이트 하면 아래와 같이 테이블에 변경 이력이 추적되는 것을 볼 수 있습니다. 

![image](./images/rev.png)

![image](./images/rev2.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

revision_type의 숫자는 `INSERT(0)`, `UPDATE(1)`, `DELETE(2)`를 의미합니다. 칼럼에 contract_name_mod가 true/false로 표시될 수도 있는데요, 해당 설정을 제거하기 위해서는 어노테이션을 아래와 같이 설정해서 주시면 됩니다.

````kotlin
@Audited(withModifiedFlag = false)
````

> 더 자세한 설명을 보고 싶으시면 [스프링 캠프](https://www.youtube.com/watch?v=fGPaj-rlN5w) 세미나에서 영한님이 발표하신 내용을 참조해보세요. 

