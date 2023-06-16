package project.study.r2dbc.core.domain.comment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table(name = "comment")
class CommentImage(
    @Id
    @Column(value = "comment_image_id")
    val commentImageId: Long? = null,

    @Column(value = "comment_id")
    val commentId: Long,

    @Column(value = "image_url")
    val imageUrl: String,

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
    val comment: Comment
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CommentImage) return false
        if (commentImageId != other.commentImageId) return false
        return true
    }

    override fun hashCode(): Int {
        return commentImageId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return commentImageId.toString()
    }
}
