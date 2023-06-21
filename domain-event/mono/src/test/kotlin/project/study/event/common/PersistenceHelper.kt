package project.study.event.common

import org.springframework.stereotype.Component
import project.study.event.common.exception.utils.fail
import project.study.event.core.domain.bookmark.entity.Bookmark
import project.study.event.core.domain.bookmark.persistence.BookmarkRepository
import project.study.event.core.domain.comment.entity.Comment
import project.study.event.core.domain.comment.persistence.CommentRepository
import project.study.event.core.domain.common.field.Deleted
import project.study.event.core.domain.notification.entity.Notification
import project.study.event.core.domain.notification.persistence.NotificationRepository
import project.study.event.core.domain.post.entity.Post
import project.study.event.core.domain.post.persistence.PostRepository
import project.study.event.core.web.post.application.exception.PostNotFoundException

@Component
class PersistenceHelper(
    private val postRepository: PostRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val commentRepository: CommentRepository,
    private val notificationRepository: NotificationRepository
) {

    fun persist(post: Post): Post {
        return postRepository.save(post)
    }

    fun findPostById(postId: Long): Post {
        return postRepository.findPostById(postId)
            ?: fail(PostNotFoundException())
    }

    fun persist(bookmark: Bookmark): Bookmark {
        return bookmarkRepository.save(bookmark)
    }

    fun findBookmarksById(bookmarkId: Long): List<Bookmark> {
        return bookmarkRepository.findAllBookmarksById(bookmarkId)
            .filter { it.delete == Deleted.FALSE }
    }

    fun persist(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    fun findCommentsById(postId: Long): List<Comment> {
        return commentRepository.findCommentsById(postId)
            .filter { it.deleted == Deleted.FALSE }
    }

    fun findNotificationById(userId: Long): Notification {
        return notificationRepository.findNotificationById(userId)
    }
}
