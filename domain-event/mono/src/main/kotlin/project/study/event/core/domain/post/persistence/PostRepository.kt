package project.study.event.core.domain.post.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import project.study.event.core.domain.post.entity.Post

interface PostRepository : JpaRepository<Post, Long> {

    @Query("SELECT p FROM post p WHERE p.postId = :postId")
    fun findPostById(postId: Long): Post?
}
