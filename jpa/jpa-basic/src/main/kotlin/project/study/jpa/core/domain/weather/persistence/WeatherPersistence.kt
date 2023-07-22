package project.study.jpa.core.domain.weather.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.jpa.core.domain.weather.entity.Weather

interface WeatherPersistence : JpaRepository<Weather, Long> {
}
