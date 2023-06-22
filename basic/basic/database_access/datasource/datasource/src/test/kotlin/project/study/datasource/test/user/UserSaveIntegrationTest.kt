package project.study.datasource.test.user

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.datasource.core.domain.user.User
import project.study.datasource.test.IntegrationTestBase

@DisplayName("[IntegrationTest] 사용자 저장 통합 테스트")
class UserSaveIntegrationTest : IntegrationTestBase() {

    @Test
    @DisplayName("사용자를 저장할 수 있다.")
    fun user_insert_test() {
        val newUser = User(1L, "Jun")
        userService.save(newUser)

        val findUser = userService.findById(newUser.userId)

        assertNotNull(findUser)
    }
}
