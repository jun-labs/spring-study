package project.study.waiting.test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import project.study.waiting.test.common.redis.RedisInitialization

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase {

    @Autowired
    lateinit var redisInitialization: RedisInitialization

    @BeforeEach
    fun setUpConfiguration(
    ) {
        redisInitialization.initRedis()
    }

    val eventIds: MutableSet<Any>
        get() = redisInitialization.eventIds!!
}
