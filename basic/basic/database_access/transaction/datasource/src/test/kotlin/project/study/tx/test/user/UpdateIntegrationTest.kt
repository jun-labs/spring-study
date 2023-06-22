package project.study.tx.test.user

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.tx.core.domain.user.User
import project.study.tx.test.IntegrationTestBase
import java.math.BigDecimal

@DisplayName("[IntegrationTest] 사용자 정보 업데이트 통합 테스트")
class UpdateIntegrationTest : IntegrationTestBase() {

    @Test
    @DisplayName("송금이 성공하면 보낸 사람의 돈은 차감되고 받은 사람의 돈은 증가한다.")
    fun user_update_test() {
        val userA = User(1L, "Jun", BigDecimal(10000))
        val userB = User(2L, "Jack", BigDecimal(1000))
        userDaoV1.save(userA)
        userDaoV1.save(userB)

        userServiceV1.transfer(
            userA.userId,
            userB.userId,
            BigDecimal(1000)
        )

        val findUserA = userDaoV1.findById(userA.userId)
        val findUserB = userDaoV1.findById(userB.userId)

        assertTrue(findUserA.money.compareTo(BigDecimal(9000)) == 0)
        assertTrue(findUserB.money.compareTo(BigDecimal(2000)) == 0)
    }

    @Test
    @DisplayName("송금이 실패하면 보낸 사람의 돈만 차감된다.")
    fun user_update_test_fail() {
        val userA = User(1L, "Jun", BigDecimal(10000))
        val userB = User(Long.MAX_VALUE, "Jack", BigDecimal(1000))
        userDaoV1.save(userA)
        userDaoV1.save(userB)

        assertThatThrownBy {
            userServiceV1.transfer(
                userA.userId,
                userB.userId,
                BigDecimal(1000)
            )
        }.isExactlyInstanceOf(IllegalStateException::class.java)

        val findUserA = userDaoV1.findById(userA.userId)
        val findUserB = userDaoV1.findById(userB.userId)

        assertTrue(findUserA.money.compareTo(BigDecimal(9000)) == 0)
        assertFalse(findUserB.money.compareTo(BigDecimal(2000)) == 0)
    }
}
