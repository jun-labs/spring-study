package project.study.event.core.web.bookmark.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronization
import project.study.event.core.domain.bookmark.persistence.BookmarkCommandRepository

@Service
class BookmarkCommandService(
    private val bookmarkCommandRepository: BookmarkCommandRepository
) {

    @Transactional
    fun deleteAll(postId: Long) {
        val bookmarks = bookmarkCommandRepository.findAllBookmarksById(postId)
        bookmarks.map { it.delete() }

        throw RuntimeException()
    }
}
