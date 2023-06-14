package project.study.storage.core.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
class User(
    @Id
    @Column(value = "user_id")
    val userId: Long? = null,

    @Column(value = "nickname")
    val nickname: String,

    @Column(value = "profile_image_url")
    val profileImageUrl: String? = null,

    @Column(value = "role")
    val role: Role
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (userId != other.userId) return false
        return true
    }

    override fun hashCode(): Int {
        return userId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return userId.toString()
    }
}
