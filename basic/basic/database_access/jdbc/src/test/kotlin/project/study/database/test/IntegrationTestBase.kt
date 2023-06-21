package project.study.database.test

import org.junit.jupiter.api.AfterEach
import org.slf4j.LoggerFactory
import project.study.database.core.domain.user.UserDao
import project.study.database.core.web.user.application.UserService

abstract class IntegrationTestBase {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val userDao: UserDao = UserDao()
    protected val userService: UserService = UserService(userDao)

    @AfterEach
    fun truncate() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        userDao.truncate()
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }
}
