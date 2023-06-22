package project.study.datasource.test.user

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.datasource.core.domain.user.User
import project.study.datasource.test.IntegrationTestBase

@DisplayName("[IntegrationTest] 사용자 삭제 통합 테스트")
class UserDeleteIntegrationTest : IntegrationTestBase() {

    @Test
    @DisplayName("사용자를 삭제한 후 조회하면 NoSuchElementException가 발생한다.")
    fun user_delete_test() {
        val newUser = User(1L, "Jun")
        userService.save(newUser)

        userService.delete(newUser.userId)

        assertThatThrownBy { userService.findById(newUser.userId) }
            .isInstanceOf(NoSuchElementException::class.java)
    }
}
