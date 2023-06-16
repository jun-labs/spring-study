package project.study.r2dbc.core.domain.comment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.post.Post
import java.time.Instant

@Table(name = "comment")
class Comment(
    @Id
    @Column(value = "comment_id")
    val commentId: Long? = null,

    @Column(value = "user_id")
    val userId: Long,

    @Column(value = "post_id")
    val postId: Long,

    @Column(value = "content")
    val content: String,

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
    val post: Post
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Comment) return false
        if (commentId != other.commentId) return false
        return true
    }

    override fun hashCode(): Int {
        return commentId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return commentId.toString()
    }
}
