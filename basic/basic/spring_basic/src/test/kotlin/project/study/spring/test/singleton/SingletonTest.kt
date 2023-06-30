package project.study.spring.test.singleton

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

@DisplayName("싱글톤 테스트")
class SingletonTest {

    @Test
    @DisplayName("싱글톤 빈이라면 하나만 생성된다.")
    fun singleton_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(UniqueSingleToneClass::class.java)

        val beanA = applicationContext.getBean(UniqueSingleToneClass::class.java)
        val beanB = applicationContext.getBean(UniqueSingleToneClass::class.java)

        assertThat(beanA).isSameAs(beanB)
    }

    @Scope("singleton")
    object UniqueSingleToneClass {

        private val log = LoggerFactory.getLogger(UniqueSingleToneClass::class.java)

        @PostConstruct
        fun init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            log.info("INIT")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}
