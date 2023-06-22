package project.study.datasource.test

import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.slf4j.LoggerFactory
import project.study.datasource.common.database.DatabaseConnectionUtil
import project.study.datasource.core.domain.user.UserDao
import project.study.datasource.core.web.user.application.UserService

abstract class IntegrationTestBase {

    private val log = LoggerFactory.getLogger(this::class.java)

    protected val userService: UserService = UserService(userDao)

    @BeforeEach
    fun truncate() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        userDao.truncate()
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    companion object {
        private lateinit var userDao: UserDao

        @JvmStatic
        @BeforeAll
        fun init() {
            val yml = "application-dev.yml"
            val dataSource = HikariDataSource()
            dataSource.jdbcUrl = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.URL)
            dataSource.username = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.USERNAME)
            dataSource.password = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.PASSWORD)
            dataSource.maximumPoolSize = 2
            dataSource.poolName = "HELLO_POOL"
            userDao = UserDao(dataSource)
        }
    }
}
