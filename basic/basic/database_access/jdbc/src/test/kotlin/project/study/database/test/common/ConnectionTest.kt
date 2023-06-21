package project.study.database.test.common

import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.jdbc.datasource.DriverManagerDataSource
import project.study.database.common.database.DatabaseConnectionUtil
import project.study.database.common.database.DatabaseConnectionUtil.PASSWORD
import project.study.database.common.database.DatabaseConnectionUtil.URL
import project.study.database.common.database.DatabaseConnectionUtil.USERNAME
import javax.sql.DataSource

@DisplayName("[UnitTest] Connection 단위 테스트")
class ConnectionTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    @DisplayName("DriverManager로 부터 Connection을 얻어올 수 있다.")
    fun get_connection_test() {
        val dbUtils = DatabaseConnectionUtil

        assertNotNull(dbUtils.getConnection())
    }

    @Test
    @DisplayName("DriverManager로 부터 얻은 Connection은 다른 객체다.")
    fun connection_equals_test() {
        val connectionA = DatabaseConnectionUtil.getConnection()
        val connectionB = DatabaseConnectionUtil.getConnection()

        assertNotEquals(connectionA, connectionB)
    }

    @Test
    fun connection_print() {
        val yml = "application.yml"
        val driverManagerDataSource = DriverManagerDataSource(
            DatabaseConnectionUtil.getValue(yml, URL),
            DatabaseConnectionUtil.getValue(yml, USERNAME),
            DatabaseConnectionUtil.getValue(yml, PASSWORD)
        )
        useDataSource(driverManagerDataSource)
    }

    @Test
    @DisplayName("커넥션 사용 테스트")
    fun connection_use_test() {
        val yml = "application.yml"
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = DatabaseConnectionUtil.getValue(yml, URL)
        dataSource.username = DatabaseConnectionUtil.getValue(yml, USERNAME)
        dataSource.password = DatabaseConnectionUtil.getValue(yml, PASSWORD)
        dataSource.maximumPoolSize = 2
        dataSource.poolName = "HELLO_POOL"
        useDataSource(dataSource)
        Thread.sleep(1000)
    }

    private fun useDataSource(dataSource: DataSource) {
        log.info("ConnectionA: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
    }

    /**
     *  최대 사용 가능한 커넥션을 초과하게 되면 지연 발생
     * */
    private fun useDataSourceOverMax(dataSource: DataSource) {
        log.info("ConnectionA: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
        log.info("ConnectionB: ${dataSource.connection}")
    }
}
