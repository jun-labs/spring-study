package project.study.tx.test

import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.slf4j.LoggerFactory
import project.study.tx.common.database.DatabaseConnectionUtil
import project.study.tx.core.domain.user.UserDaoV1
import project.study.tx.core.domain.user.UserDaoV2
import project.study.tx.core.web.user.application.UserServiceV1
import project.study.tx.core.web.user.application.UserServiceV2

abstract class IntegrationTestBase {

    private val log = LoggerFactory.getLogger(this::class.java)

    val userServiceV1 = UserServiceV1(userDaoV1)
    val userServiceV2 = UserServiceV2(dataSource, userDaoV2)

    @BeforeEach
    fun truncate() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        userDaoV1.truncate()
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    companion object {
        lateinit var userDaoV1: UserDaoV1
        lateinit var userDaoV2: UserDaoV2
        val dataSource = HikariDataSource()

        @JvmStatic
        @BeforeAll
        fun init() {
            val yml = "application-dev.yml"
            dataSource.jdbcUrl = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.URL)
            dataSource.username = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.USERNAME)
            dataSource.password = DatabaseConnectionUtil.getValue(yml, DatabaseConnectionUtil.PASSWORD)
            dataSource.maximumPoolSize = 2
            dataSource.poolName = "HELLO_POOL"
            userDaoV1 = UserDaoV1(dataSource)
            userDaoV2 = UserDaoV2(dataSource)
        }
    }
}
