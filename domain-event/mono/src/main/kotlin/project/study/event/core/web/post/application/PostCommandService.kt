package project.study.event.core.web.post.application

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.event.common.exception.utils.fail
import project.study.event.core.domain.post.event.PostDeleteEvent
import project.study.event.core.domain.post.persistence.PostRepository
import project.study.event.core.web.post.application.exception.PostNotFoundException

@Service
class PostCommandService(
    private val postRepository: PostRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun publishEvent(postId: Long) {
        val findPost = postRepository.findPostById(postId)
            ?: fail(PostNotFoundException())
        applicationEventPublisher.publishEvent(PostDeleteEvent(findPost.postId!!))
        findPost.delete()
    }
}
