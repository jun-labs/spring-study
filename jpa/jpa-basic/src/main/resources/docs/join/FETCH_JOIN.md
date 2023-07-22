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
