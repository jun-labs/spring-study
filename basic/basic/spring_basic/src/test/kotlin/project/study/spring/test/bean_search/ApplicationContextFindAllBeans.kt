package project.study.spring.test.bean_search

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import project.study.spring.common.configuration.AppConfig

@DisplayName("ApplicationContext 빈 조회 테스트")
class ApplicationContextFindAllBeans {

    private val log = LoggerFactory.getLogger(this::class.java)

    val applicationContext: AnnotationConfigApplicationContext =
        AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("스프링 컨테이너에 등록된 빈을 조회할 수 있다.")
    fun findAllBeans() {
        val beanNames = applicationContext.beanDefinitionNames
        beanNames.asList()
            .forEach(::println)
    }

    /**
     * 모든 빈 출력하기
     * BeanDefinition.ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
     * BeanDefinition.ROLE_SUPPORT: 스프링 내부에서 사용하는 빈
     * BeanDefinition.ROLE_INFRASTRUCTURE:
     * */
    @Test
    @DisplayName("스프링 컨테이너에 등록된 빈을 조회할 수 있다.")
    fun findAllBeanV2() {
        val beanNames = applicationContext.beanDefinitionNames

        for (beanName in beanNames) {
            val definition = applicationContext.getBeanDefinition(beanName)
            if (definition.role == BeanDefinition.ROLE_APPLICATION) {
                log.info("BeanDefinition: $definition")
            }
        }
    }
}
