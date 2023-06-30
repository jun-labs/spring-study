package project.study.spring.test.bean_search

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import project.study.spring.core.domain.member.persistence.MemberRepository
import project.study.spring.core.domain.member.persistence.MemoryMemberRepository

@DisplayName("동일 빈 조회 테스트")
class SameBeanFindTest {

    private val applicationContext = AnnotationConfigApplicationContext(SameBeanConfiguration::class.java)

    @Test
    @DisplayName("동일한 타입의 빈이 두 개 이상 있다면 NoUniqueBeanDefinitionException이 발생한다.")
    fun same_type_bean_over_two_test() {
        assertThatThrownBy { applicationContext.getBean(MemberRepository::class.java) }
            .isExactlyInstanceOf(NoUniqueBeanDefinitionException::class.java)
            .hasMessage(message)
    }

    @Test
    @DisplayName("동일한 타입의 빈이 두 개 이상 있더라도 빈 이름을 명시하면 오류가 발생하지 않는다.")
    fun bean_type_definition_test() {
        val bean = applicationContext.getBean("memberRepository", MemberRepository::class.java)

        assertNotNull(bean)
    }

    @Test
    @DisplayName("특정 타입으로 빈을 조회할 수 있다.")
    fun bean_search_by_type() {
        val beans = applicationContext.getBeansOfType(MemberRepository::class.java)

        assertEquals(2, beans.size)
    }

    @Configuration
    class SameBeanConfiguration {
        @Bean
        fun memberRepository(): MemberRepository {
            return MemoryMemberRepository()
        }

        @Bean
        fun memberRepositoryV2(): MemberRepository {
            return MemoryMemberRepository()
        }
    }

    companion object {
        private const val message =
            "No qualifying bean of type 'project.study.inflearn.core.domain.member.persistence.MemberRepository' available: expected single matching bean but found 2: memberRepository,memberRepository2"
    }
}
