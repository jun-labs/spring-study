package project.study.spring.test.bean_scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class PrototypeTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val beanA = applicationContext.getBean(PrototypeBeanClass::class.java)
        val beanB = applicationContext.getBean(PrototypeBeanClass::class.java)

        assertThat(beanA).isNotSameAs(beanB)

        beanA.destroy()
        beanB.destroy()
    }

    @Scope("prototype")
    class PrototypeBeanClass {

        private val log = LoggerFactory.getLogger(this::class.java)

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
