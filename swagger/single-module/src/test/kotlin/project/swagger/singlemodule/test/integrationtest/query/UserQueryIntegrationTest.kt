package project.swagger.singlemodule.test.integrationtest.query

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import project.swagger.singlemodule.core.domain.entity.User
import project.swagger.singlemodule.core.web.application.UserService
import project.swagger.singlemodule.core.web.exception.UserNotFoundException
import project.swagger.singlemodule.test.integrationtest.IntegrationTestBase

@DisplayName("[IntegrationTest] 사용자 조회 통합 테스트")
class UserQueryIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var userService: UserService

    @Test
    @DisplayName("사용자가 존재하면 PK로 조회할 수 있다.")
    fun user_find_by_id_test() {
        val newUserId = userService.save(User(30L, "Hello"))

        val findUser = userService.findUserById(newUserId)

        assertNotNull(findUser)
    }

    @Test
    @DisplayName("사용자 PK로 조회했을 때, 사용자가 존재하지 않는다면 UserNotFoundException이 발생한다.")
    fun user_find_by_id_failure_test() {
        userService.save(User(30L, "Hello"))

        assertThatThrownBy { userService.findUserById(Long.MAX_VALUE) }
            .isExactlyInstanceOf(UserNotFoundException::class.java)
            .isInstanceOf(RuntimeException::class.java)
    }

    @Test
    @DisplayName("사용자가 존재하면 이름으로 조회할 수 있다.")
    fun user_find_by_name_test() {
        userService.save(User(30L, "Hello"))

        val findUser = userService.findUserByName("Hello")

        assertNotNull(findUser)
    }

    @Test
    @DisplayName("사용자 이름으로 조회했을 때, 사용자가 존재하지 않는다면 UserNotFoundException이 발생한다.")
    fun user_find_by_name_failure_test() {
        userService.save(User(30L, "Hello"))

        assertThatThrownBy { userService.findUserByName("UUUUQQ") }
            .isExactlyInstanceOf(UserNotFoundException::class.java)
            .isInstanceOf(RuntimeException::class.java)
    }

    @Test
    @DisplayName("사용자가 존재하면 목록을 조회할 수 있다.")
    fun users_find_test() {
        userService.save(User(30L, "Hello"))

        val findUsers = userService.findAllUsers()

        assertFalse(findUsers.isEmpty())
    }
}
