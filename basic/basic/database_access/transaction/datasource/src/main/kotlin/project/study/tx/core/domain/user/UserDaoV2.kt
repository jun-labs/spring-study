package project.study.tx.core.domain.user

import org.slf4j.LoggerFactory
import org.springframework.jdbc.support.JdbcUtils
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.sql.*
import javax.sql.DataSource

@Repository
class UserDaoV2(
    private val dataSource: DataSource
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun save(
        connection: Connection,
        user: User
    ) {
        val sql = "INSERT INTO user(user_id, nickname, money) values(?, ?, ?)"

        var preparedStatement: PreparedStatement? = null
        try {
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setLong(1, user.userId)
            preparedStatement.setString(2, user.nickname)
            preparedStatement.setBigDecimal(3, user.money)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(preparedStatement, preparedStatement?.resultSet)
        }
    }

    fun findById(userId: Long): User {
        val query = "SELECT * FROM user WHERE user_id = ?"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet?
        try {
            connection = getConnection()
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setLong(1, userId)

            resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return User(
                    resultSet.getLong(USER_ID),
                    resultSet.getString(NICKNAME),
                    resultSet.getBigDecimal(MONEY)
                )
            }
            throw NoSuchElementException()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(preparedStatement, preparedStatement?.resultSet)
        }
        throw IllegalStateException()
    }

    fun update(
        connection: Connection,
        userId: Long,
        price: BigDecimal
    ) {
        val query = "UPDATE user SET money=? WHERE user_id=?"

        var preparedStatement: PreparedStatement? = null
        try {
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setBigDecimal(1, price)
            preparedStatement.setLong(2, userId)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(preparedStatement, preparedStatement?.resultSet)
        }
    }

    fun findById(
        connection: Connection,
        userId: Long
    ): User {
        val query = "SELECT * FROM user WHERE user_id = ?"

        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet?
        try {
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setLong(1, userId)

            resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return User(
                    resultSet.getLong(USER_ID),
                    resultSet.getString(NICKNAME),
                    resultSet.getBigDecimal(MONEY)
                )
            }
            throw NoSuchElementException()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(preparedStatement, preparedStatement?.resultSet)
        }
        throw IllegalStateException()
    }

    fun getConnection(): Connection {
        return dataSource.connection
    }

    private fun close(
        statement: Statement?,
        resultSet: ResultSet?
    ) {
        JdbcUtils.closeResultSet(resultSet)
        JdbcUtils.closeStatement(statement)
    }

    fun truncate(connection: Connection) {
        val query = "TRUNCATE TABLE user"

        var preparedStatement: PreparedStatement? = null
        try {
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(preparedStatement, preparedStatement?.resultSet)
        }
    }

    companion object {
        private const val USER_ID = "user_id"
        private const val NICKNAME = "nickname"
        private const val MONEY = "money"
    }
}
