package project.study.tx.test.user

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.tx.core.domain.user.User
import project.study.tx.test.IntegrationTestBase
import java.math.BigDecimal

@DisplayName("[IntegrationTest] 사용자 정보 업데이트 통합 테스트")
class UpdateIntegrationTestV2 : IntegrationTestBase() {

    @Test
    @DisplayName("송금이 실패해도 돈의 증감이 이루어지지 않는다.")
    fun user_update_test_fail() {
        val userA = User(1L, "Jun", BigDecimal(10000))
        val userB = User(Long.MAX_VALUE, "Jack", BigDecimal(1000))
        userDaoV1.save(userA)
        userDaoV1.save(userB)

        userServiceV2.transfer(
            userA.userId,
            userB.userId,
            BigDecimal(1000)
        )

        val findUserA = userServiceV1.findById(userA.userId)
        val findUserB = userServiceV1.findById(userB.userId)

        assertTrue(findUserA.money.compareTo(BigDecimal(10000)) == 0)
        assertTrue(findUserB.money.compareTo(BigDecimal(1000)) == 0)
    }
}
