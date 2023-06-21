package project.study.event.core.web.comment.presentation

import org.springframework.web.bind.annotation.*
import project.study.event.core.web.comment.application.CommentCommandService
import project.study.event.core.web.comment.presentation.request.CommentWriteRequest

@RestController
@RequestMapping("/api/posts")
class CommentCommandAPI(
    private val commentCommandService: CommentCommandService
) {

    @PostMapping("/{postId}/comments")
    fun writeComment(
        @PathVariable postId: Long,
        @RequestBody request: CommentWriteRequest
    ) {
        val newComment = request.toEntity(postId)
        commentCommandService.save(newComment)
    }
}
