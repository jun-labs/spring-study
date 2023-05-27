## 💫 JPA

JPA learning repository.


&nbsp;&nbsp; [1. FetchJoin의 원리](#FetchJoin의-원리) <br/>
&nbsp;&nbsp; [2. 연관관계 없이 조인하기](#연관관계-없이-조인) <br/>

<br/><br/><br/><br/>

## FetchJoin의 원리

FetchJoin은 실제 조인하는 `시점에 조인하는 테이블의 모든 칼럼을 다 SELECT`해서 가져옵니다. 따라서 이후 조인한 테이블의 데이터를 조회해도 추가 쿼리가 발생하지 않습니다. 어떻게 이것이 가능한지 한 번 살펴보겠습니다.


<br/><br/><br/><br/>

테스트 환경에 대해 먼저 살펴보겠습니다. 테이블 구조는 아래와 같습니다. 하나의 도시(City)에는 여러 개의 시(District)가 존재하는 일대 다의 구조입니다.

````sql
CREATE TABLE city
(
    city_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(15)
) ENGINE = INNODB;

CREATE TABLE district
(
    district_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(15),
    city_id     BIGINT NOT NULL,

    CONSTRAINT fk_city_city_id FOREIGN KEY (city_id) REFERENCES city (city_id)
) ENGINE = INNODB
````

<br/><br/><br/><br/><br/><br/><br/><br/>

컨트롤러에서 districtId를 @Pathvariable로 받아 JpaRepository에서 데이터를 찾아옵니다. 

```kotlin
@RestController
@RequestMapping("/api/users")
class UserUpdateAPI(
    private val userUpdateService: UserUpdateService
) {

    @PostMapping("/{userId}")
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
```kotlin
@Service
class DistrictQueryService(private val districtJpaRepository: DistrictJpaRepository) {

    @Transactional(readOnly = true)
    fun searchDistrictById(districtId: Long): DistrictResponse {
        val findDistrict = districtJpaRepository.findDistinctByDistrictId(districtId)
            .get()
        return DistrictResponse(findDistrict)
    }
}
```

```kotlin
interface DistrictJpaRepository : JpaRepository<District, Long> {

    @Query("SELECT d FROM District d INNER JOIN (FETCH) d.city WHERE d.districtId = :districtId")
    fun findDistinctByDistrictId(@Param("districtId") districtId: Long): Optional<District>
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

응답을 내려줄 때 조인한 도시(City)에서 getName( ) 메서드를 통해 도시의 이름을 가져옵니다. 

```kotlin
class DistrictResponse(district: District) {

    private val districtId: Long = district.getDistrictId()!!
    private val districtName: String = district.getName()
    private val cityName: String = district.getCityName()

    fun getDistrictId(): Long {
        return districtId
    }

    fun getDistrictName(): String {
        return districtName
    }

    fun getCityName(): String {
        return cityName
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

먼저 FetchJoin을 사용한 경우 입니다.

````kotlin
interface DistrictJpaRepository : JpaRepository<District, Long> {

    @Query("SELECT d FROM District d INNER JOIN FETCH d.city WHERE d.districtId = :districtId")
    fun findDistinctByDistrictId(@Param("districtId") districtId: Long): Optional<District>
}
````

<br/><br/><br/><br/><br/><br/>

실제 쿼리를 살펴보면 아래와 같이 도시(city) 테이블의 모든 칼럼을 SELECT 해오는 것을 볼 수 있습니다.

````sql
SELECT d1_0.district_id,
       c1_0.city_id,
       c1_0.name,
       d1_0.name
FROM district d1_0
JOIN city c1_0 ON c1_0.city_id = d1_0.city_id
WHERE d1_0.district_id = ?
````

<br/><br/><br/><br/><br/><br/><br/><br/>

반면 단순히 INNER JOIN만 사용한 경우를 살펴보겠습니다.

```kotlin
interface DistrictJpaRepository : JpaRepository<District, Long> {

    @Query("SELECT d FROM District d INNER JOIN d.city WHERE d.districtId = :districtId")
    fun findDistinctByDistrictId(@Param("districtId") districtId: Long): Optional<District>
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이 경우 `SELECT 절을 살펴보면 시(District)의 칼럼만` 조회해서 가져오는 것을 볼 수 있습니다. 따라서 이를 정리해 보면 `FetchJoin은 사용하는 시점에 조인하는 테이블의 모든 칼럼을 SELECT 해서 가져오며` 이 때문에 추가 쿼리가 발생하지 않는 것을 알 수 있습니다.

```sql
SELECT d1_0.district_id,
       d1_0.city_id,
       d1_0.name
FROM district d1_0
JOIN city c1_0 ON c1_0.city_id = d1_0.city_id
WHERE d1_0.district_id = ?;

SELECT c1_0.city_id,
       c1_0.name
FROM city c1_0
WHERE c1_0.city_id = ?
```

<br/><br/><br/><br/><br/><br/><br/><br/>

## 연관관계 없이 조인

테이블을 매핑하다보니 `두 엔티티가 반드시 연관관계를 가지는게 옳을까?`에 대한 의문이 들었습니다. 말로 설명하면 와닿지 않기 때문에 바로 예제로 살펴보겠습니다. 하나의 도시에는 여러 명의 고용자가 있는 경우입니다. 즉 도시:고용자 = 1:N의 관계가 되는데요, 이때 고용자들 측에서 반드시 `City를 객체로 가지고, 있어야 할까?`에 대한 의문이 들었습니다. 

```kotlin
@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var employeeId: Long? = null

    @Column
    private var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private val city: City            // 반드시 연관관계가 있어야 할까?

    ......
    
}
```

````kotlin
@Entity
class City{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cityId: Long? = null,

    @Column
    private var name: String,

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private val lastModifiedAt: Instant

    ......
    
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

일반적으로 매핑 한다면 아래와 같이 City 객체를 Employee 엔티티가 가지고, 있어야 합니다. `JPA는 객체를 기준으로 사고`하기 때문입니다. 하지만 생각해 보면 조인할 때 필요한 것은 `외래키 값` 밖에 없으며 이 경우 추가 쿼리에 대한 불필요한 걱정도 할 필요가 없습니다.

```kotlin

import jakarta.persistence.ManyToOne

```kotlin
@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var employeeId: Long? = null

    @Column
    private var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private val city: City

    ......

}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

따라서 연관관계를 제거하고 도시의 외래키 값만 가지도록 아래와 같이 수정했습니다. 

```kotlin
```kotlin
@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var employeeId: Long? = null

    @Column
    private var name: String? = null

    @Column
    private val cityId: Long? = null    // 외래키 값만 가지도록 리팩토링

    ......
    
}
```


<br/><br/><br/><br/><br/><br/><br/><br/>

이제 실험을 해보겠습니다. QueryDsl과 Projections를 사용해 필요한 칼럼만 조인을 걸었습니다. 

```kotlin
@RestController
@RequestMapping("/api/cities")
class CityQueryAPI(private val employeeQueryService: EmployeeQueryService) {

    @GetMapping("/{cityId}/employees/{employeeId}")
    fun searchEmployeeById(
        @PathVariable cityId: Long,
        @PathVariable employeeId: Long
    ): ResponseEntity<EmployeeResponse> {
        val body = employeeQueryService.findEmployeeById(cityId, employeeId)
        return ResponseEntity.ok(body)
    }
}
```

```kotlin
@Service
class EmployeeQueryService(private val employeeQueryRepository: EmployeeQueryRepository) {
    fun findEmployeeById(
        cityId: Long,
        employeeId: Long
    ): EmployeeResponse {
        return employeeQueryRepository.findEmployeeById(cityId, employeeId)
    }
}
```

```kotlin
@Repository
class EmployeeQueryRepository(private val queryFactory: JPAQueryFactory) {

    fun findEmployeeById(
        cityId: Long,
        employeeId: Long
    ): EmployeeResponse {
        return queryFactory.select(
            constructor(
                EmployeeResponse::class.java,
                employee.employeeId,
                employee.name,
                city.name
            )
        ).from(employee)
            .join(city)
            .on(city.cityId.eq(cityId))
            .where(employee.employeeId.eq(employeeId))
            .fetchOne()!!
    }
}
```

````http request
### Employee Query API
GET localhost:8088/api/cities/1/employees/1
Content-Type: application/json
````


<br/><br/><br/><br/><br/><br/><br/><br/>

추가로 employee 테이블에 외래키를 추가해 줍니다.

````sql
ALTER TABLE employee
    ADD CONSTRAINT fk_city_employee_city_id
        FOREIGN KEY (city_id) REFERENCES city (city_id);
````

<br/><br/><br/><br/><br/><br/><br/><br/>

이후 실행된 쿼리를 보면 조인이 원하는 대로 된 것을 볼 수 있습니다. 즉 이를 요약해 보면 `조인을 위해 두 엔티티가 반드시 연관관계를 가질 필요가 없다. 어차피 조인에 필요한 것은 외래키 값이다.`라는 것을 알 수 있습니다.

```sql
SELECT e1_0.employee_id,
       e1_0.name,
       c1_0.name
FROM employee e1_0
JOIN city c1_0 ON c1_0.city_id = ?
WHERE e1_0.employee_id = ?
```

<br/><br/><br/><br/><br/><br/><br/><br/>

물론 결과도 원하는 대로 잘 나옵니다. 

![image](./images/join_query_result.png)

<br/>
