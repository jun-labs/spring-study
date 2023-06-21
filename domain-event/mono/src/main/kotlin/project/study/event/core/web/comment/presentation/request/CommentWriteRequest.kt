package project.study.event.core.web.comment.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import project.study.event.core.domain.comment.entity.Comment

data class CommentWriteRequest(
    @JsonProperty("content") val content: String
) {
    fun toEntity(postId: Long): Comment {
        return Comment(
            commentId = null,
            userId = USER_ID,
            postId = postId,
            content = content
        )
    }
}

private const val USER_ID = 1L
