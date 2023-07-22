package project.study.waiting.test.common.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisInitialization {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    fun initRedis() {
        redisTemplate.opsForZSet().removeRange(EVENT_KEY, 0, 100000)
    }

    val eventIds: MutableSet<Any>?
        get() = redisTemplate.opsForZSet().range(EVENT_KEY, 0, 100000)

    /**
     *  테스트를 위해 Event Key는 간략히 설계
     * */
    companion object {
        private const val EVENT_KEY = "ORDER_EVENT"
    }
}
