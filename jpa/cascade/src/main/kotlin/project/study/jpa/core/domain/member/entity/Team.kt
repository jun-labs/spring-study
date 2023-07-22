package project.study.jpa.core.domain.member.entity

import jakarta.persistence.*

@Entity
class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var teamId: Long? = null,

    @Column(name = "name")
    val name: String
) {
    @OneToMany(mappedBy = "team", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    private var _members: MutableList<Member> = ArrayList()

    val members: List<Member>
        get() = _members

    fun register(members: MutableList<Member>) {
        members.forEach { member -> member.register(this) }
        this._members = members
    }

    fun remove(member: Member) {
        _members.remove(member)
    }

    fun removeAll() {
        _members = mutableListOf()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Team) return false
        if (teamId != other.teamId) return false
        return true
    }

    override fun hashCode(): Int {
        return teamId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return teamId.toString()
    }
}
