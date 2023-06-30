package project.study.spring.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.support.GenericXmlApplicationContext
import project.study.spring.core.web.member.MemberService

class XmlAppContextTest {

    @Test
    @Disabled
    @DisplayName("")
    fun test() {
        val applicationContext = GenericXmlApplicationContext("appConfig.xml")

        val memberService = applicationContext.getBean("memberService", MemberService::class.java)

        assertThat(memberService).isExactlyInstanceOf(MemberService::class.java)

    }
}
