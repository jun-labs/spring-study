package project.study.r2dbc.core.domain.hashtag

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.club.Club
import java.time.Instant

@Table(name = "club_hashtag")
class ClubHashtag(
    @Id
    @Column(value = "club_hashtag_id")
    val clubHashtagId: Long? = null,

    @Column(value = "club_id")
    val clubId: Long,

    @Column(value = "hashtag_id")
    val hashtagId: Long,

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
    val club: Club
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClubHashtag) return false
        if (clubHashtagId != other.clubHashtagId) return false
        return true
    }

    override fun hashCode(): Int {
        return clubHashtagId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return clubHashtagId.toString()
    }
}
