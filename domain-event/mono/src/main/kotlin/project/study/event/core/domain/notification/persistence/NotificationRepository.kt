package project.study.event.core.domain.notification.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import project.study.event.core.domain.notification.entity.Notification

interface NotificationRepository : JpaRepository<Notification, Long> {

    @Query("SELECT n FROM notification n WHERE n.sourceId = :userId")
    fun findNotificationById(userId: Long): Notification
}
