package project.study.r2dbc.core.domain.hashtag

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table("hashtag")
class Hashtag(
    @Id
    @Column(value = "hashtag_id")
    val hashtagId: Long? = null,

    @Column(value = "tag_name")
    val tagName: String,

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
        if (other !is Hashtag) return false
        if (hashtagId != other.hashtagId) return false
        return true
    }

    override fun hashCode(): Int {
        return hashtagId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return hashtagId.toString()
    }
}
