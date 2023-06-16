package project.study.r2dbc.core.domain.comment.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.comment.Comment
import reactor.core.publisher.Mono

interface CommentQueryRepository : ReactiveCrudRepository<Comment, Long> {

    @Query("SELECT * FROM comment AS c JOIN post p on c.post_id = p.post_id WHERE c.post_id = :postId")
    fun findTestById(postId: Long): Mono<Comment>?
}
