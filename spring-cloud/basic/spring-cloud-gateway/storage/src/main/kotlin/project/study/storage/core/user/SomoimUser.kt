package project.study.storage.core.user

class SomoimUser(
    val userId: Long,
    val nickname: String,
    val profileImageUrl: String? = null,
    val role: Role
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SomoimUser) return false

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

    override fun toString(): String {
        return userId.toString()
    }

    companion object {
        fun of(
            userId: Long,
            nickname: String,
            profileImageUrl: String? = null,
            role: String
        ): SomoimUser {
            return SomoimUser(
                userId,
                nickname,
                profileImageUrl,
                Role.getRole(role)
            )
        }
    }
}
