package project.study.datasource.core.domain.user

class User(
    val userId: Long,
    val nickname: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (userId != other.userId) return false
        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

    override fun toString(): String {
        return userId.toString()
    }
}
