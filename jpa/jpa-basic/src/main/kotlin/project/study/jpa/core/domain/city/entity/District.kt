package project.study.jpa.core.domain.city.entity

import jakarta.persistence.*

@Entity
class District(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var districtId: Long? = null,

    @Column
    private var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private var city: City
) {
    fun getDistrictId(): Long? {
        return districtId
    }

    fun getName(): String {
        return name;
    }

    fun getCityName(): String {
        return city.getName()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is District) return false
        if (districtId != other.districtId) return false
        return true
    }

    override fun hashCode(): Int {
        return districtId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return districtId.toString()
    }
}
