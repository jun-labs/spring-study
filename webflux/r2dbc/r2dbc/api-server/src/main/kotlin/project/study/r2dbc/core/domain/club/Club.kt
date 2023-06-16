package project.study.r2dbc.core.domain.club

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table(name = "club")
class Club(
    @Id
    @Column(value = "club_id")
    val clubId: Long? = null,

    @Column(value = "user_id")
    val userId: Long,

    @Column(value = "title")
    val title: String,

    @Column(value = "content")
    val content: String,

    @Column(value = "logo_image_url")
    val logoImageUrl: String,

    @Column(value = "member_count")
    val memberCount: Int,

    @Column(value = "created_by")
    val createdBy: Long,

    @Column(value = "created_at")
    val createdAt: Instant,

    @Column(value = "last_modified_by")
    val lastModifiedBy: Long? = null,

    @Column(value = "last_modified_at")
    val lastModifiedAt: Instant? = null,

    @Column(value = "deleted")
    val deleted: Deleted
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Club) return false
        if (clubId != other.clubId) return false

        return true
    }

    override fun hashCode(): Int {
        return clubId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return clubId.toString()
    }
}
