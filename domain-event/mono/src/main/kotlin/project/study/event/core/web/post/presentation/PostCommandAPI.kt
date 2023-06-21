package project.study.event.core.web.post.presentation

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.study.event.core.web.post.application.PostCommandService

@RestController
@RequestMapping("/api/posts")
class PostCommandAPI(
    private val postCommandService: PostCommandService
) {

    @DeleteMapping("/{postId}")
    fun delete(
        @PathVariable postId: Long
    ) {
        postCommandService.publishEvent(postId)
    }
}
