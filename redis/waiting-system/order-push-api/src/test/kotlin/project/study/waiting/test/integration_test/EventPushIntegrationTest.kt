package project.study.waiting.test.integration_test

import com.fasterxml.uuid.Generators
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import project.study.waiting.common.core.order.OrderEvent
import project.study.waiting.core.order.application.out.EventPushPort
import project.study.waiting.test.IntegrationTestBase
import java.math.BigDecimal
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@DisplayName("[IntegrationTest] 이벤트 대기열 저장 테스트")
class EventPushIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var eventPushPort: EventPushPort

    @Test
    @DisplayName("이벤가 발생하면 이벤트 아이디가 대기열에 저장된다.")
    fun event_push_test() {
        val orderEvent = OrderEvent(
            Generators.timeBasedGenerator().generate().toString(),
            1L,
            1L,
            BigDecimal(3000)
        )
        eventPushPort.push(orderEvent)

        val eventIds = eventIds
        assertTrue(eventIds.isNotEmpty())
        assertEquals(1, eventIds.size)
    }

    @Test
    @DisplayName("여러 이벤트가 동시에 발생하면 이벤트 개수 만큼 아이디 목록이 대기열에 저장된다.")
    fun events_push_test() {
        val executorService = Executors.newFixedThreadPool(32)
        val countDownLatch = CountDownLatch(10000)
        for (event in 1..10000) {
            executorService.submit {
                try {
                    val orderEvent = OrderEvent(
                        Generators.timeBasedGenerator().generate().toString(),
                        1L,
                        1L,
                        BigDecimal(3000)
                    )
                    eventPushPort.push(orderEvent)
                } finally {
                    countDownLatch.countDown()
                }
            }
        }

        countDownLatch.await()

        val eventIds = eventIds
        assertTrue(eventIds.isNotEmpty())
        assertEquals(10000, eventIds.size)
    }
}
