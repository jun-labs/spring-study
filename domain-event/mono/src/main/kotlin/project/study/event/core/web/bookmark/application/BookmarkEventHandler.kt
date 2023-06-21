package project.study.event.core.web.bookmark.application

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import project.study.event.core.domain.post.event.PostDeleteEvent

@Component
class BookmarkEventHandler(
    private val bookmarkService: BookmarkCommandService
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun listenEvent(event: PostDeleteEvent) {
        val post = event.postId
        bookmarkService.deleteAll(post)
        throw IllegalArgumentException()
    }
}
