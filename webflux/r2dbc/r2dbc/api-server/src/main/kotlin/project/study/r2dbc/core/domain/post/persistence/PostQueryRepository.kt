package project.study.r2dbc.core.domain.post.persistence

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.post.Post

interface PostQueryRepository : ReactiveCrudRepository<Post, Long> {


}
