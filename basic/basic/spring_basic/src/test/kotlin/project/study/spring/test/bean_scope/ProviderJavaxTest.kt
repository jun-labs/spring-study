package project.study.spring.test.bean_scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.test.context.ActiveProfiles
import javax.inject.Provider

@SpringBootTest
@ActiveProfiles("test")
class ProviderJavaxTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(
            ClientBeanClass::class.java
        )

        val bean = applicationContext.getBean(ClientBeanClass::class.java)

        val prototypeA = bean.prototypeBeanClass
        prototypeA.increase()

        val prototypeB = bean.prototypeBeanClass
        prototypeB.increase()

        assertThat(prototypeA).isNotSameAs(prototypeB)
        assertEquals(1, prototypeA.count)
        assertEquals(1, prototypeB.count)

        prototypeB.destroy()
        prototypeA.destroy()
        //destroy()
    }

    @Scope("singleton")
    companion object ClientBeanClass {
        private val log = LoggerFactory.getLogger(this::class.java)

        lateinit var provider: Provider<PrototypeBean2Class>

        val prototypeBeanClass: PrototypeBean2Class
            get() = provider.get()

        @Autowired
        lateinit var applicationContext: AnnotationConfigApplicationContext

        @PostConstruct
        fun init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            log.info("INIT: $this")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY: $this")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }

        @Scope("prototype")
        class PrototypeBean2Class {

            private val log = LoggerFactory.getLogger(this::class.java)

            private var _count = 0

            val count: Int
                get() = _count

            fun increase() {
                this._count++
            }

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

}
