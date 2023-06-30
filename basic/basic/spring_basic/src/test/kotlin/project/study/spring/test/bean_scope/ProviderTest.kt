package project.study.spring.test.bean_scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class ProviderTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val bean = applicationContext.getBean(PrototypeBeanClass::class.java)

        val prototypeA = bean.prototypeBean
        prototypeA.increase()

        val prototypeB = bean.prototypeBean
        prototypeB.increase()

        assertThat(prototypeA).isNotSameAs(prototypeB)
        assertEquals(1, prototypeA.count)
        assertEquals(1, prototypeB.count)

        prototypeB.destroy()
        prototypeA.destroy()
        bean.destroy()
    }

    @Scope("prototype")
    class PrototypeBeanClass {

        // 스프링에 의존적인 기술
        @Autowired
        private lateinit var provider: ObjectProvider<PrototypeBeanClass>

        val prototypeBean: PrototypeBeanClass
            get() = provider.`object`

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
            log.info("INIT: $this")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY: $this")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}
