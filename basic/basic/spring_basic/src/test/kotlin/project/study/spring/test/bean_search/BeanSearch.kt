package project.study.spring.test.bean_search

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import project.study.spring.common.configuration.AppConfig
import project.study.spring.core.web.member.MemberService
import project.study.spring.core.web.member.MemberServiceImpl

@DisplayName("빈 조회 테스트")
class BeanSearch {

    private val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 이름(인터페이스)으로 조회할 수 있다.")
    fun find_bean_name_interface_test() {
        val memberService = applicationContext.getBean("memberService", MemberService::class.java)

        assertThat(memberService)
            .isExactlyInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("빈 이름(구체 클래스)으로 조회할 수 있다.")
    fun find_bean_name_concrete_class_test() {
        val memberService = applicationContext.getBean("memberService", MemberServiceImpl::class.java)

        assertThat(memberService)
            .isExactlyInstanceOf(MemberServiceImpl::class.java)
    }
}
