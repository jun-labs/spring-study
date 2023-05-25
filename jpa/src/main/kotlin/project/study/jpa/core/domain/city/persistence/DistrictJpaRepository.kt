package project.study.jpa.core.domain.city.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import project.study.jpa.core.domain.city.entity.District
import java.util.*

interface DistrictJpaRepository : JpaRepository<District, Long> {

    @Query("SELECT d FROM District d INNER JOIN d.city WHERE d.districtId = :districtId")
    fun findDistinctByDistrictId(@Param("districtId") districtId: Long): Optional<District>
}
