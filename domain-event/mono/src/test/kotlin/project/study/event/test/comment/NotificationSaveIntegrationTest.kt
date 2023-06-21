package project.study.event.test.comment

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import project.study.event.common.PersistenceHelper
import project.study.event.core.domain.comment.entity.Comment
import project.study.event.core.web.comment.application.CommentCommandService

@SpringBootTest
@ActiveProfiles("test")
class NotificationSaveIntegrationTest {

    @Autowired
    private lateinit var persistenceHelper: PersistenceHelper

    @Autowired
    private lateinit var commentCommandService: CommentCommandService

    @Test
    @DisplayName("댓글을 저장하면 알림이 저장된다.")
    fun notification_save_test() {
        val userId = 1L
        commentCommandService.save(Comment(null, userId, 1L, "Hello"))

        val findNotification = persistenceHelper.findNotificationById(userId)
        assertNotNull(findNotification)
    }
}
