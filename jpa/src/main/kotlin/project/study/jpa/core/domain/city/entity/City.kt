package project.study.jpa.core.domain.city.entity

import jakarta.persistence.*

@Entity
class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cityId: Long? = null,

    @Column
    private var name: String
) {
    fun getCityId(): Long? {
        return cityId
    }

    fun getName(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is City) return false
        if (cityId != other.cityId) return false
        return true
    }

    override fun hashCode(): Int {
        return cityId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return cityId.toString()
    }
}
