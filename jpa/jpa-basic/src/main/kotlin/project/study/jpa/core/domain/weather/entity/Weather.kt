package project.study.jpa.core.domain.weather.entity

import jakarta.persistence.*

@Entity
class Weather(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var weatherId: Long? = null,

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private val isTrue: Int,

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private val isToday: Boolean
) {
    fun getWeatherId(): Long? {
        return weatherId
    }

    fun isTrue(): Int {
        return isTrue
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Weather) return false
        if (weatherId != other.weatherId) return false
        return true
    }

    override fun hashCode(): Int {
        return weatherId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return weatherId.toString()
    }
}
