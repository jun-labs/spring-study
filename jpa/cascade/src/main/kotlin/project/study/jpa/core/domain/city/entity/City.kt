package project.study.jpa.core.domain.city.entity

import jakarta.persistence.*

@Entity
class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cityId: Long? = null,

    @Column(name = "name")
    private var name: String
) {
    @OneToMany(mappedBy = "city", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    private var _districts: MutableList<District> = ArrayList()

    val districts: MutableList<District>
        get() = _districts

    fun register(districts: MutableList<District>) {
        districts.forEach { district -> district.register(this) }
        this._districts = districts
    }

    fun remove(hwagok: District) {
        _districts.remove(hwagok)
    }

    fun removeAll() {
        _districts = ArrayList()
    }

    override fun toString(): String {
        return cityId.toString()
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
}
