package project.swagger.singlemodule.test.integrationtest.command

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import project.swagger.singlemodule.core.domain.entity.User
import project.swagger.singlemodule.core.web.application.UserService
import project.swagger.singlemodule.test.integrationtest.IntegrationTestBase

@DisplayName("[IntegrationTest] 사용자 명령 통합 테스트")
class UserCommandIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var userService: UserService

    @Test
    @DisplayName("사용자가 저장되면 이를 조회할 수 있다.")
    fun user_save_test() {
        val newUserId = userService.save(User(30L, "Hello"))

        val findUser = userService.findUserById(newUserId)

        assertNotNull(findUser)
    }
}
