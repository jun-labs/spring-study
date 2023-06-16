package project.study.r2dbc.core.domain.post

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table("post_image")
class PostImage(
    @Id
    @Column(value = "post_image_id")
    val postImageId: Long? = null,

    @Column(value = "post_id")
    val postId: Long,

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
    val post: Post
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PostImage) return false
        if (postImageId != other.postImageId) return false
        return true
    }

    override fun hashCode(): Int {
        return postImageId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return postImageId.toString()
    }
}
