package project.study.spring.test.singleton

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import project.study.spring.common.configuration.AppConfig
import project.study.spring.core.domain.member.persistence.MemoryMemberRepository
import project.study.spring.core.web.member.MemberServiceImpl
import project.study.spring.core.web.order.OrderServiceImpl

@DisplayName("Configuration Singleton 테스트")
class ConfigurationSingletonTest {

    @Test
    @DisplayName("스프링 컨테이너에서 관리되는 빈은 싱글톤이다.")
    fun singleton_test() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val orderService = applicationContext.getBean("orderService", OrderServiceImpl::class.java)
        val memberService = applicationContext.getBean("memberService", MemberServiceImpl::class.java)

        val memberRepositoryA = orderService.memberRepository
        val memberRepositoryB = memberService.memberRepository

        assertEquals(memberRepositoryA, memberRepositoryB)
    }

    @Test
    fun singleton_print_test() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val orderService = applicationContext.getBean("orderService", OrderServiceImpl::class.java)
        val memberService = applicationContext.getBean("memberService", MemberServiceImpl::class.java)

        // 호출되지 않는다.
        val memberRepository = applicationContext.getBean("memberRepository", MemoryMemberRepository::class.java)

        val memberRepositoryA = orderService.memberRepository
        val memberRepositoryB = memberService.memberRepository

        assertEquals(memberRepositoryA, memberRepositoryB)
    }

    @Test
    fun cglib_print_test() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val appConfig = applicationContext.getBean(AppConfig::class.java)
        println(appConfig)
    }
}
