package project.study.spring.common.configuration

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import project.study.spring.core.domain.discount.DiscountPolicy
import project.study.spring.core.domain.discount.FixDiscountPolicy
import project.study.spring.core.domain.member.persistence.MemberRepository
import project.study.spring.core.domain.member.persistence.MemoryMemberRepository
import project.study.spring.core.web.member.MemberService
import project.study.spring.core.web.member.MemberServiceImpl
import project.study.spring.core.web.order.OrderService
import project.study.spring.core.web.order.OrderServiceImpl

//@Configuration
class AppConfig {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     *  빈으로 등록된 타입을 보고 결정한다.
     *  즉 MemberServiceImpl이 아닌 MemberService를 보고 결정.
     * */
    @Bean
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        log.info(">> MemberRepository")
        return MemoryMemberRepository()
    }

    @Bean
    fun orderService(): OrderService {
        log.info(">> OrderService")
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }
}
