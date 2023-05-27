package project.study.jpa.core.domain.city.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.jpa.core.domain.city.entity.City

interface CityJpaRepository : JpaRepository<City, Long> {
}
