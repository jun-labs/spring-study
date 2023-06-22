package project.study.datasource.test.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.datasource.core.domain.user.User
import project.study.datasource.test.IntegrationTestBase

@DisplayName("[IntegrationTest] 사용자 정보 업데이트 통합 테스트")
class UpdateIntegrationTest : IntegrationTestBase() {

    @Test
    @DisplayName("닉네임을 업데이트하면 닉네임이 변경된다.")
    fun user_update_test() {
        val newUser = User(1L, "Jun")
        userService.save(newUser)

        userService.update(newUser.userId, "Hello World")

        val findUser = userService.findById(newUser.userId)
        assertEquals("Hello World", findUser.nickname)
    }
}
