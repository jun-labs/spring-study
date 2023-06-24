package project.study.spring.core.domain.member

class Member(
    val memberId: Long,
    val name: String,
    val grade:Grade
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Member) return false
        if (memberId != other.memberId) return false
        return true
    }

    override fun hashCode(): Int {
        return memberId.hashCode()
    }

    override fun toString(): String {
        return memberId.toString()
    }
}
