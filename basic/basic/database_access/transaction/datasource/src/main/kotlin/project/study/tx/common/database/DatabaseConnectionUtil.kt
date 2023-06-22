package project.study.tx.common.database

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Slf4j
object DatabaseConnectionUtil {
    private val log = LoggerFactory.getLogger(this::class.java)

    private const val KEY = 0
    private const val VALUE = 1
    private const val DELIMITER = ": "
    private const val FILE_NAME = "application.yml"
    const val URL = "url"
    const val USERNAME = "username"
    const val PASSWORD = "password"

    private val url: String = getValue(FILE_NAME, URL)
    private val username: String = getValue(FILE_NAME, USERNAME)
    private val password: String = getValue(FILE_NAME, PASSWORD)
    private val connections = HashSet<Connection>()

    fun getConnection(): Connection {
        try {
            val connection = DriverManager.getConnection(url, username, password)
            log.info("Connections: $connection, Class: ${connection.javaClass}")
            return connection
        } catch (ex: SQLException) {
            throw IllegalStateException()
        }
    }

    fun getValue(
        fileName: String,
        value: String
    ): String {
        var inputStream: InputStream? = null
        try {
            val resource = ClassPathResource(fileName)
            inputStream = resource.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            while (reader.readLine().apply { line = this } != null) {
                val property = line?.trim()?.split(DELIMITER) ?: continue
                if (property[KEY] == value) {
                    return property[VALUE]
                }
            }
        } catch (e: IOException) {
            throw IllegalStateException()
        } finally {
            inputStream?.close()
        }
        throw IllegalStateException()
    }
}
