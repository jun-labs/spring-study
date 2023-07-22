package project.study.jpa.test.city

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import project.study.jpa.core.domain.city.entity.City
import project.study.jpa.core.domain.city.entity.District
import project.study.jpa.core.domain.city.persistence.CityJpaRepository
import project.study.jpa.core.domain.city.persistence.DistrictJpaRepository

@SpringBootTest
@ActiveProfiles("test")
class CityCascadeTest {

    @Autowired
    lateinit var cityRepository: CityJpaRepository

    @Autowired
    lateinit var districtRepository: DistrictJpaRepository

    @BeforeEach
    fun init() {
        cityRepository.deleteAll()
        districtRepository.deleteAll()
    }

    @Test
    @DisplayName("CascadeType에 Persist가 있다면 부모가 영속화 될 때 자식도 영속화 된다.")
    fun cascade_persist_test() {
        val city = City(null, "서울")
        val seoul = District(null, "강서구")

        city.register(mutableListOf(seoul))
        cityRepository.save(city)

        city.districts
            .forEach { district -> assertNotNull(district.districtId) }
    }

    @Test
    @DisplayName("orphanRemoval=true 라면 부모가 삭제될 때 자식도 함께 삭제된다.")
    fun orphan_removal_test() {
        val city = City(null, "서울")
        val gangseo = District(null, "강서구")
        val hwagok = District(null, "화곡")

        city.register(mutableListOf(gangseo, hwagok))
        cityRepository.save(city)

        cityRepository.delete(city)

        assertThatThrownBy { districtRepository.findById(gangseo.districtId!!).get() }
            .isExactlyInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    @DisplayName("orphanRemoval=true 라면 부모의 객체 라이프사이클을 따르게 된다.")
    fun orphan_removal_life_cycle_test() {
        val city = City(null, "서울")
        val gangseo = District(null, "강서구")
        val hwagok = District(null, "화곡")

        city.register(mutableListOf(gangseo, hwagok))
        cityRepository.save(city)

        city.remove(hwagok)
        cityRepository.save(city)

        assertEquals(1, cityRepository.findAll().size)
        assertEquals(1, districtRepository.findAll().size)
    }
}
