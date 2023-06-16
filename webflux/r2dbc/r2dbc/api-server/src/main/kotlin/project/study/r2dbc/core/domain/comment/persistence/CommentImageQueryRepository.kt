package project.study.r2dbc.core.domain.comment.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.comment.Comment
import project.study.r2dbc.core.domain.comment.CommentImage
import reactor.core.publisher.Mono

interface CommentImageQueryRepository : ReactiveCrudRepository<CommentImage, Long> {

    @Query("SELECT * FROM comment_image AS ci JOIN comment c on c.comment_id = ci.comment_id JOIN post p on p.post_id = c.post_id WHERE ci.comment_id = :commentId")
    fun findTestById(commentId: Long): Mono<CommentImage>?
}
