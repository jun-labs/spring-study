package project.study.event.core.web.bookmark.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.event.core.domain.bookmark.persistence.BookmarkRepository

@Service
class BookmarkCommandService(
    private val bookmarkRepository: BookmarkRepository
) {

    @Transactional
    fun deleteAll(postId: Long) {
        val bookmarks = bookmarkRepository.findAllBookmarksById(postId)
        bookmarks.map { it.delete() }
    }
}
