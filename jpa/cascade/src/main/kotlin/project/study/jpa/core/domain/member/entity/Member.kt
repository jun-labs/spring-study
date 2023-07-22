package project.study.jpa.core.domain.member.entity

import jakarta.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null,

    @Column
    val _name: String
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private var team: Team? = null

    fun register(team: Team) {
        this.team = team
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Member) return false
        if (memberId != other.memberId) return false
        return true
    }

    override fun hashCode(): Int {
        return memberId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return memberId.toString()
    }
}
