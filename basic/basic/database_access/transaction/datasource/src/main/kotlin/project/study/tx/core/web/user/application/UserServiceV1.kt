package project.study.tx.core.web.user.application

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import project.study.tx.core.domain.user.User
import project.study.tx.core.domain.user.UserDaoV1
import java.math.BigDecimal

@Service
class UserServiceV1(
    private val userDao: UserDaoV1
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun transfer(
        fromId: Long,
        toId: Long,
        money: BigDecimal
    ) {
        val fromUser = userDao.findById(fromId)
        val toUser = userDao.findById(toId)

        userDao.update(fromId, fromUser.money - money)
        validation(toUser)
        userDao.update(toId, toUser.money + money)
    }

    private fun validation(toUser: User) {
        if (toUser.userId == Long.MAX_VALUE) {
            throw IllegalStateException()
        }
    }

    fun findById(userId: Long): User {
        return userDao.findById(userId)
    }
}
