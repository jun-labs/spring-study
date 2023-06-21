package project.study.event.core.domain.comment.event

class AlarmEvent(
    val sourceId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AlarmEvent) return false
        if (sourceId != other.sourceId) return false
        return true
    }

    override fun hashCode(): Int {
        return sourceId.hashCode()
    }

    override fun toString(): String {
        return sourceId.toString()
    }
}
