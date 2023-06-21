package project.study.event.core.domain.notification.entity

import jakarta.persistence.*

@Entity(name = "notification")
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val notificationId: Long? = null,

    @Column
    val sourceId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Notification) return false
        if (notificationId != other.notificationId) return false
        return true
    }

    override fun hashCode(): Int {
        return notificationId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return notificationId.toString()
    }
}
