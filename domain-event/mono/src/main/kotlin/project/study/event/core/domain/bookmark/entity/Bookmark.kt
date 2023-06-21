package project.study.event.core.domain.bookmark.entity

import jakarta.persistence.*
import project.study.event.core.domain.common.field.Deleted
import project.study.event.core.domain.post.entity.Post

@Entity(name = "bookmark")
class Bookmark(
    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookmarkId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post
) {
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')", name = "deleted")
    private var _deleted: Deleted? = Deleted.FALSE

    val delete: Deleted
        get() = _deleted!!

    fun delete() {
        this._deleted = Deleted.TRUE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bookmark) return false

        if (bookmarkId != other.bookmarkId) return false
        return true
    }

    override fun hashCode(): Int {
        return bookmarkId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return bookmarkId.toString()
    }
}
