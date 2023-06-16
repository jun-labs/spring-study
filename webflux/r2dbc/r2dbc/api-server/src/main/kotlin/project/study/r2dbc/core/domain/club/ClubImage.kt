package project.study.r2dbc.core.domain.club

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table("club_image")
class ClubImage(
    @Id
    @Column(value = "club_image_id")
    val clubImageId: Long? = null,

    @Column(value = "club_id")
    val clubId: Long,

    @Column(value = "image_url")
    val imageUrl: String? = null,

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
    val club: Club? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClubImage) return false
        if (clubImageId != other.clubImageId) return false
        return true
    }

    override fun hashCode(): Int {
        return clubImageId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return clubImageId.toString()
    }
}
