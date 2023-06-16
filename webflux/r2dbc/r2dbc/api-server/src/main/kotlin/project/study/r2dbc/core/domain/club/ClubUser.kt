package project.study.r2dbc.core.domain.club

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.user.User
import java.time.Instant

@Table(name = "club_user")
class ClubUser(
    @Id
    @Column(value = "club_user_id")
    val clubUserId: Long? = null,

    @Column(value = "club_id")
    val clubId: Long,

    @Column(value = "user_id")
    val userId: Long,

    @Column(value = "created_by")
    val createdBy: Long,

    @Column(value = "created_at")
    val createdAt: Instant,

    @Column(value = "last_modified_by")
    val lastModifiedBy: Long? = null,

    @Column(value = "last_modified_at")
    val lastModifiedAt: Instant? = null,

    @Column(value = "deleted")
    val deleted: Deleted,

    @Transient
    val user: User? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClubUser) return false
        if (clubUserId != other.clubUserId) return false
        return true
    }

    override fun hashCode(): Int {
        return clubUserId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return clubUserId.toString()
    }
}
