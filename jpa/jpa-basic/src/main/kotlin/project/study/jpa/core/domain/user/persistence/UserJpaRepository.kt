package project.study.jpa.core.domain.user.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.jpa.core.domain.user.entity.User

interface UserJpaRepository : JpaRepository<User, Long> {
}
