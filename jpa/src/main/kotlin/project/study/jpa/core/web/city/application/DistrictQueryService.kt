package project.study.jpa.core.web.city.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.jpa.core.domain.city.persistence.DistrictJpaRepository
import project.study.jpa.core.web.city.presentation.response.DistrictResponse

@Service
class DistrictQueryService(private val districtJpaRepository: DistrictJpaRepository) {

    @Transactional(readOnly = true)
    fun searchDistrictById(districtId: Long): DistrictResponse {
        val findDistrict = districtJpaRepository.findDistinctByDistrictId(districtId)
            .get()
        return DistrictResponse(findDistrict)
    }
}
