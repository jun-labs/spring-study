package project.study.tx.core.web.user.application

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import project.study.tx.core.domain.user.User
import project.study.tx.core.domain.user.UserDaoV2
import java.math.BigDecimal
import java.sql.Connection
import javax.sql.DataSource

@Service
class UserServiceV2(
    private val dataSource: DataSource,
    private val userDao: UserDaoV2
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun transfer(
        fromId: Long,
        toId: Long,
        money: BigDecimal
    ) {
        val connection = dataSource.connection
        connection.autoCommit = false
        try {
            val fromUser = userDao.findById(connection, fromId)
            val toUser = userDao.findById(connection, toId)

            userDao.update(
                connection,
                fromId,
                fromUser.money - money
            )
            validation(toUser)
            userDao.update(
                connection,
                toId,
                toUser.money + money
            )
        } catch (e: Exception) {
            log.error("Rollback: $e")
            connection.rollback()
        } finally {
            release(connection)
        }
    }

    private fun validation(toUser: User) {
        if (toUser.userId == Long.MAX_VALUE) {
            throw IllegalStateException()
        }
    }

    private fun release(connection: Connection) {
        try {
            // 커넥션 풀 고려
            connection.autoCommit = true
            connection.close()
        } catch (e: Exception) {
            log.error("Error")
        }
    }
}
