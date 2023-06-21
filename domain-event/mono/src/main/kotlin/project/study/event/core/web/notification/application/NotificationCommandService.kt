package project.study.event.core.web.notification.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.event.core.domain.notification.entity.Notification
import project.study.event.core.domain.notification.persistence.NotificationRepository

@Service
class NotificationCommandService(
    private val notificationRepository: NotificationRepository
) {

    @Transactional
    fun save(notification: Notification) {
        notificationRepository.save(notification)
    }
}
