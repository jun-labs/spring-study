package project.study.spring.test.bean_search

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import project.study.spring.common.configuration.AppConfig

@DisplayName("BeanDefinition 빈 조회 테스트")
class BeanDefinitionTest {

    private val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("BeanDefinition의 이름을 조회할 수 있다.")
    fun bean_definition_names_search_test() {
        val bean = applicationContext.beanDefinitionNames
        bean.forEach(::println)
    }
}
