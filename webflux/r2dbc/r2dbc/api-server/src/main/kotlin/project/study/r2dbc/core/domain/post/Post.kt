package project.study.r2dbc.core.domain.post

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table("post")
class Post(
    @Id
    @Column(value = "post_id")
    var postId: Long? = null,

    @Column(value = "user_id")
    var userId: Long,

    @Column(value = "club_id")
    var clubId: Long,

    @Column(value = "content")
    var content: String,

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
        if (other !is Post) return false
        if (postId != other.postId) return false
        return true
    }

    override fun hashCode(): Int {
        return postId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return postId.toString()
    }
}
