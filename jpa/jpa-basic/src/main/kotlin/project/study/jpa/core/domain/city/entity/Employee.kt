package project.study.jpa.core.domain.city.entity

import jakarta.persistence.*

@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var employeeId: Long? = null

    @Column
    private var name: String? = null

    @Column
    private val cityId: Long? = null

    fun getCityId(): Long {
        return cityId!!
    }

    fun getName(): String {
        return name!!
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Employee) return false
        if (employeeId != other.employeeId) return false
        return true
    }

    override fun hashCode(): Int {
        return employeeId?.hashCode() ?: 0
    }
}
