package project.study.event.core.domain.post.event

class PostDeleteEvent(
    val postId: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PostDeleteEvent) return false
        if (postId != other.postId) return false
        return true
    }

    override fun hashCode(): Int {
        return postId.hashCode()
    }

    override fun toString(): String {
        return postId.toString()
    }
}
