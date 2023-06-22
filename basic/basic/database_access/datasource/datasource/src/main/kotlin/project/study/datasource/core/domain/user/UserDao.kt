package project.study.datasource.core.domain.user

import org.slf4j.LoggerFactory
import org.springframework.jdbc.support.JdbcUtils
import org.springframework.stereotype.Repository
import java.sql.*
import javax.sql.DataSource

@Repository
class UserDao(
    private val dataSource: DataSource
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun save(user: User) {
        val sql = "INSERT INTO user(user_id, nickname) values(?, ?)"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = getConnection()
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
            connection = getConnection()
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
            connection = getConnection()
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
            connection = getConnection()
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

    fun getConnection(): Connection {
        return dataSource.connection
    }

    private fun close(
        connection: Connection?,
        statement: Statement?,
        resultSet: ResultSet?
    ) {
        JdbcUtils.closeResultSet(resultSet)
        JdbcUtils.closeStatement(statement)
        JdbcUtils.closeConnection(connection)
    }

    fun truncate() {
        val query = "TRUNCATE TABLE user"

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = getConnection()
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
