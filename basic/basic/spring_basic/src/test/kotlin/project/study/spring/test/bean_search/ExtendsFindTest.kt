package project.study.spring.test.bean_search

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import project.study.spring.core.domain.discount.DiscountPolicy
import project.study.spring.core.domain.discount.FixDiscountPolicy
import project.study.spring.core.domain.discount.RateDiscountPolicy

@DisplayName("빈 상속 관계 조회 테스트")
class ExtendsFindTest {

    private val log = LoggerFactory.getLogger(this::class.java)
    private val applicationContext = AnnotationConfigApplicationContext(TestConfiguration::class.java)

    @Test
    @DisplayName("하위 타입으로 빈을 조회할 수 있다.")
    fun bean_search_by_subtype() {
        val bean = applicationContext.getBean(RateDiscountPolicy::class.java)

        assertNotNull(bean)
    }

    /**
     *  모든 빈 조회
     * */
    @Test
    @DisplayName("특정 타입으로 빈을 조회할 수 있다.")
    fun bean_search_by_specific_type() {
        val beans = applicationContext.getBeansOfType(Any::class.java)

        log.info("$beans")
    }

    @Configuration
    class TestConfiguration {
        @Bean
        fun fixDiscountPolicy(): DiscountPolicy {
            return FixDiscountPolicy()
        }

        @Bean
        fun rateDiscountPolicy(): DiscountPolicy {
            return RateDiscountPolicy()
        }
    }
}
