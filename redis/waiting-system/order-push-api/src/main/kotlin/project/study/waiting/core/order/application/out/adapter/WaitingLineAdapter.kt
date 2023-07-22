package project.study.waiting.core.order.application.out.adapter

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Component
import project.study.waiting.common.core.order.OrderEvent
import project.study.waiting.core.order.application.out.EventPushPort

@Component
class WaitingLineAdapter(
    val redisTemplate: RedisTemplate<String, Any>
) : EventPushPort {

    override fun push(event: OrderEvent) {
        opsZorZSet().add(EVENT_NAME, event.id!!, System.currentTimeMillis().toDouble())
    }

    private fun opsZorZSet(): ZSetOperations<String, Any> {
        return redisTemplate.opsForZSet()
    }

    companion object {
        private const val EVENT_NAME = "ORDER_EVENT"
    }
}
