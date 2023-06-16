package project.study.r2dbc.core.domain.post.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.post.Post
import project.study.r2dbc.core.domain.post.PostImage
import reactor.core.publisher.Mono

interface PostImageQueryRepository : ReactiveCrudRepository<PostImage, Long> {

    @Query("SELECT * FROM post_image AS pi JOIN post p on p.post_id = pi.post_id WHERE pi.post_id = :postId")
    fun findTestById(postId: Long): Mono<PostImage>?
}
