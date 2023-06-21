package project.study.database.core.web.user.application

import org.springframework.stereotype.Service
import project.study.database.core.domain.user.User
import project.study.database.core.domain.user.UserDao

@Service
class UserService(
    private val userDao: UserDao
) {

    fun findById(userId: Long): User {
        return userDao.findById(userId)
    }

    fun save(user: User) {
        userDao.save(user)
    }

    fun update(
        userId: Long,
        nickname: String
    ) {
        userDao.update(userId, nickname)
    }

    fun delete(userId: Long) {
        userDao.delete(userId)
    }
}
