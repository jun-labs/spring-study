## 💬 JPA

Hibernate/JPA learning repository.

![image](resources/images/img.png)

<div align="center">

[![Release](https://img.shields.io/badge/-%F0%9F%93%9A%20Spring_Data_JPA-brightgreen)](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
[![Release](https://img.shields.io/badge/-🚀_Hibernate_ORM-green)](https://hibernate.org/orm/documentation/6.2/)
</div>

<br/><br/><br/><br/>

## CasCade

JPA에서 옵션으로 사용할 수 있는 CasCade는 데이터베이스의 [참조 무결성](https://en.wikipedia.org/wiki/Referential_integrity) 입니다. 참조 무결성이란 데이터베이스 상의 참조가 모두 유효함을 말합니다.

>Referential integrity is a property of data stating that all its references are valid. In the context of relational databases, it requires that if a value of one attribute (column) of a relation (table) references a value of another attribute (either in the same or a different relation), then the referenced value must exist.

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 아래와 같이 사용할 수 있는데요, 각각의 옵션에 대해 살펴보겠습니다.

```kotlin
@Entity
class City(
    ......
) {
    @OneToMany(mappedBy = "city", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    private var _districts: MutableList<District> = ArrayList()
}
```
<br/><br/><br/><br/><br/><br/><br/><br/>

CascadeType은 Enum으로 제공되며 아래와 같은 설정이 있습니다.

```kotlin
public enum CascadeType { 

    /** Cascade all operations */
    ALL, 

    /** Cascade persist operation */
    PERSIST,

    /**
     * - 엔티티 상태를 병합 할 때, 연관된 엔티티도 함께 병합
     * - 트랜잭션이 종료되고 DETACHED 상태에서 엔티티가 merge( )를 수행하게 되면 연관 엔티티의 추가 및 수정사항도 함께 적용
    */
    MERGE,

    /**
     * 엔티티를 삭제할 때, 연관된 엔티도 함께 삭제
     */
    REMOVE,

    /**
     * 상위 엔티티를 REFRESH 할 때, 연관된 엔티티도 모두 REFRESH
     */
    REFRESH,

    /**
     * 부모 엔티티가 detach()를 수행하게 되면, 연관된 엔티티도 DETACHED 상태가 되어 변경사항이 반영 X
     */   
    DETACH
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 엔티티의 상태가 변경될 때 `상태 변화를 전파시키는 옵션`입니다. 엔티티의 상태는 크게 네 가지가 존재하는데요, 이는 아래와 같습니다.

- Transient: 객체를 생성하거나 값을 부여해도 JPA/Hibernate가 객체에 대해 아무것도 모르는 상태.

- Persistent: 엔티티를 저장한 순간 JPA가 이를 감지하는 상태. 이때 주의해야 할 점은 save 메서드를 호출 했다고 해서 곧 바로 DB에 저장되지는 않습니다. JPA가 영속 상태로 관리하고 있다가 후에 데이터를 저장합니다. `Write Behind`

- Detached: JPA가 더이상 관리하지 않는 상태. JPA가 제공해주는 기능들을 사용하려면 다시 영속 상태로 만들어야 합니다.
- Removed: JPA가 관리하는 상태이긴 하지만, 실제 커밋이 일어날 때 삭제되는 상태.

> 이와 관련된 설명/자료가 많기 때문에 헷깔리는 상황에 대해서만 살펴보겠습니다. 

<br/><br/><br/><br/><br/><br/><br/><br/>


## CasCadeType.REMOVE vs orphanRemoval = true

결론부터 말하면 CascadeType.REMOVE와 orphanRemoval = true는 부모 엔티티를 삭제하면 자식 엔티티도 삭제합니다. 하지만 부모 엔티티에서 자식 엔티티 제거할 때 CascadeType.REMOVE는 자식 엔티티가 그대로 남아있는 반면, orphanRemoval = true는 자식 엔티티를 제거합니다.

```kotlin
class MemberCascadeTest {

    @Test
    @DisplayName("orphanRemoval 옵션 없이 CascadeType만 Remove라면 연관관계를 끊어도 자식이 삭제되지 않는다.")
    fun cascade_remove_test() {
        val messi = Member(null, "Messi")
        val xavi = Member(null, "Xavi")
        val team = Team(null, "FC Barcelona")

        team.register(mutableListOf(messi, xavi))
        teamJpaRepository.save(team)

        // 모두 제거해도 남아있음
        team.removeAll()

        // true 
        assertEquals(2, memberJpaRepository.findAll().size)
    }
}
```

```kotlin
class CityCascadeTest {
    
    @Test
    @DisplayName("orphanRemoval=true 라면 부모의 객체 라이프사이클을 따르게 된다.")
    fun orphan_removal_life_cycle_test() {
        val city = City(null, "서울")
        val gangseo = District(null, "강서구")
        val hwagok = District(null, "화곡")

        city.register(mutableListOf(gangseo, hwagok))
        cityRepository.save(city)

        // 자식 하나 제거
        city.remove(hwagok)
        cityRepository.save(city)

        // true
        assertEquals(1, cityRepository.findAll().size)
        assertEquals(1, districtRepository.findAll().size)
    }
}
```
