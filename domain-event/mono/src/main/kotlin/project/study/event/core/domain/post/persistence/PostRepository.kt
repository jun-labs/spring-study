package project.study.event.core.domain.post.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.event.core.domain.post.entity.Post

interface PostRepository : JpaRepository<Post, Long>
