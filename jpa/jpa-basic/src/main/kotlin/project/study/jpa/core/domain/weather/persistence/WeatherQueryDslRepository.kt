package project.study.jpa.core.domain.weather.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import project.study.jpa.core.domain.weather.entity.QWeather.Companion.weather
import project.study.jpa.core.domain.weather.entity.Weather

@Repository
class WeatherQueryDslRepository(private val queryFactory: JPAQueryFactory) {

    fun findWeatherByIdWithIsToday(weatherId: Long): Weather {
        return queryFactory.selectFrom(weather)
            .where(weather.isToday.eq(true))
            .fetchOne()!!
    }

    fun findWeatherByIdWithIsTrue(weatherId: Long): Weather {
        return queryFactory.selectFrom(weather)
            .where(weather.isTrue.eq(1))
            .fetchOne()!!
    }
}
