package project.study.jpa.core.web.city.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.jpa.core.domain.city.persistence.CityJpaRepository

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
