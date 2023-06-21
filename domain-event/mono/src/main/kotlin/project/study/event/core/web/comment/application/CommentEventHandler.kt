package project.study.event.core.web.comment.application

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import project.study.event.core.domain.post.event.PostDeleteEvent

@Component
class CommentEventHandler(
    private val commentCommandService: CommentCommandService
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun listenEvent(event: PostDeleteEvent) {
        commentCommandService.deleteAll(event.postId)
    }
}
