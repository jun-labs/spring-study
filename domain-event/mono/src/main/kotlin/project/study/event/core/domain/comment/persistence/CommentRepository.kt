package project.study.event.core.domain.comment.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import project.study.event.core.domain.comment.entity.Comment

interface CommentRepository : JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM comment c WHERE c.postId = :postId")
    fun findCommentsById(postId: Long): List<Comment>
}
