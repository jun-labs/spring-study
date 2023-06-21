package project.study.event.core.web.comment.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.event.core.domain.comment.entity.Comment
import project.study.event.core.domain.comment.persistence.CommentRepository

@Service
class CommentCommandService(
    private val commentRepository: CommentRepository
) {

    @Transactional
    fun save(comment: Comment) {
        comment.publish()
        commentRepository.save(comment)
    }

    @Transactional
    fun deleteAll(postId: Long) {
        val findComments = commentRepository.findCommentsById(postId)
        findComments.map { it.delete() }
    }
}
