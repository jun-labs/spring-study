package project.study.jpa.core.web.user.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.jpa.core.domain.user.persistence.UserJpaRepository

@Service
class UserUpdateService(private val userJpaRepository: UserJpaRepository) {

    @Transactional
    fun updateNickname(
        userId: Long,
        nickname: String,
        weight: Double
    ) {
        val findUser = userJpaRepository.findById(userId).orElseThrow();
        findUser.updateProfile(nickname, weight)
    }
}
