package project.study.event.core.domain.post.entity

import jakarta.persistence.*
import project.study.event.core.domain.common.field.Deleted

@Entity(name = "post")
class Post(
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long? = null,

    @Column(name = "title")
    private var _title: String,

    @Column(name = "content")
    private var _content: String
) {
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')", name = "deleted")
    private var deleted: Deleted? = Deleted.FALSE

    fun delete() {
        this.deleted = Deleted.TRUE
    }

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
