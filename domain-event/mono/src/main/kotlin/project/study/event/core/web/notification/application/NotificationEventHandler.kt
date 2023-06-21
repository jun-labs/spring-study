package project.study.event.core.web.notification.application

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import project.study.event.core.domain.comment.event.AlarmEvent
import project.study.event.core.domain.notification.entity.Notification

@Component
class NotificationEventHandler(
    private val notificationCommandService: NotificationCommandService
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun listenEvent(alarmEvent: AlarmEvent) {
        val newNotification = Notification(null, alarmEvent.sourceId)
        notificationCommandService.save(newNotification)
    }
}
