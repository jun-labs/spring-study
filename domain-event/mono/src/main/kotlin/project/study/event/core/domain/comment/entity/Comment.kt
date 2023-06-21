package project.study.event.core.domain.comment.entity

import jakarta.persistence.*
import org.springframework.data.domain.AbstractAggregateRoot
import project.study.event.core.domain.comment.event.AlarmEvent
import project.study.event.core.domain.common.field.Deleted

@Entity(name = "comment")
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null,

    @Column
    val userId: Long,

    @Column
    val postId: Long,

    @Column
    val content: String
) : AbstractAggregateRoot<Comment>() {
    @Column
    private var _createdBy: Long = userId

    @Column
    private var _lastModifiedBy: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private var _deleted: Deleted? = Deleted.FALSE

    val createdBy: Long
        get() = _createdBy

    val lastModifiedBy: Long?
        get() = _lastModifiedBy

    val deleted: Deleted
        get() = _deleted!!

    fun publish() {
        registerEvent(AlarmEvent(userId))
    }

    fun delete() {
        this._deleted = Deleted.TRUE
    }

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
