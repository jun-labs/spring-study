package project.study.event.core.domain.notification.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.event.core.domain.notification.entity.Notification

interface NotificationRepository : JpaRepository<Notification, Long>
