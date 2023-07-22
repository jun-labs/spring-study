package project.study.jpa.core.domain.city.entity

import jakarta.persistence.*
import project.study.jpa.core.domain.member.entity.Member

@Entity
class District(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val districtId: Long? = null,

    @Column
    private var _name: String
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private var city: City? = null

    fun register(city: City) {
        this.city = city
    }

    fun remove() {
        this.city = null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Member) return false
        if (districtId != other.memberId) return false
        return true
    }

    override fun hashCode(): Int {
        return districtId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return districtId.toString()
    }
}
