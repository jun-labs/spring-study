package project.study.database.core.domain.user

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import project.study.database.common.database.DatabaseConnectionUtil
import java.sql.*

@Repository
class UserDao {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val connectionUtils = DatabaseConnectionUtil

    fun save(user: User) {
        val sql = "INSERT INTO user(user_id, nickname) values(?, ?)"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = connectionUtils.getConnection()
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setLong(1, user.userId)
            preparedStatement.setString(2, user.nickname)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(connection, preparedStatement, preparedStatement?.resultSet)
        }
    }

    fun update(
        userId: Long,
        nickname: String
    ) {
        val query = "UPDATE user SET nickname=? WHERE user_id=?"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = connectionUtils.getConnection()
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, nickname)
            preparedStatement.setLong(2, userId)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(connection, preparedStatement, preparedStatement?.resultSet)
        }
    }

    fun findById(userId: Long): User {
        val query = "SELECT * FROM user WHERE user_id = ?"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet?
        try {
            connection = connectionUtils.getConnection()
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setLong(1, userId)

            resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return User(
                    resultSet.getLong(USER_ID),
                    resultSet.getString(NICKNAME)
                )
            }
            throw NoSuchElementException()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(connection, preparedStatement, preparedStatement?.resultSet)
        }
        throw IllegalStateException()
    }

    fun delete(userId: Long) {
        val sql = "DELETE FROM user WHERE user_id = ? "
        var connection: Connection? = null
        var pstmt: PreparedStatement? = null
        try {
            connection = connectionUtils.getConnection()
            pstmt = connection.prepareStatement(sql)
            pstmt.setLong(1, userId)
            pstmt.executeUpdate()
            return
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(connection, pstmt, pstmt?.resultSet)
        }
        throw IllegalStateException()
    }

    private fun close(
        connection: Connection?,
        statement: Statement?,
        resultSet: ResultSet?
    ) {
        try {
            statement?.close()
        } catch (exception: SQLException) {
            log.error("Statement Error")
        }

        try {
            connection?.close()
        } catch (exception: SQLException) {
            log.error("Connection Error")
        }

        try {
            resultSet?.close()
        } catch (exception: SQLException) {
            log.error("ResultSet Error")
        }
    }

    fun truncate() {
        val query = "TRUNCATE TABLE user"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = connectionUtils.getConnection()
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.execute()
        } catch (exception: SQLException) {
            log.error("Database Error")
            exception.printStackTrace()
        } finally {
            close(connection, preparedStatement, preparedStatement?.resultSet)
        }
    }

    companion object {
        private const val USER_ID = "user_id"
        private const val NICKNAME = "nickname"
    }
}
