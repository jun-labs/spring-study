package project.study.r2dbc.core.domain.user.persistence

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.user.User

interface UserQueryRepository : ReactiveCrudRepository<User, Long>
