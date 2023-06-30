package project.study.spring.test.bean_scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class SingletonBeanWithPrototypeBeanTest {

    @Test
    @DisplayName("프로토타입 빈을 사용하면 매번 새로운 객체가 생성된다.")
    fun test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val prototypeBeanA = applicationContext.getBean(PrototypeBean::class.java)
        prototypeBeanA.increase()

        val prototypeBeanB = applicationContext.getBean(PrototypeBean::class.java)
        prototypeBeanB.increase()

        assertEquals(1, prototypeBeanA.count)
        assertEquals(1, prototypeBeanB.count)
    }

    @Test
    @DisplayName("")
    fun test2() {
        val applicationContext = AnnotationConfigApplicationContext(SingletonBean::class.java)
        val prototypeBeanA = applicationContext.getBean(SingletonBean::class.java)
        SingletonBean.increase()

        val prototypeBeanB = applicationContext.getBean(SingletonBean::class.java)
        SingletonBean.increase()

        assertEquals(2, SingletonBean.count)
    }

    /**
     *  생성 시점에 이미 의존성이 주입된다.
     * */
    @Scope("singleton")
    object SingletonBean {
        private var _count = 0
        private val log = LoggerFactory.getLogger(this::class.java)

        val count: Int
            get() = _count

        fun increase() {
            _count++
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

    @Scope("prototype")
    class PrototypeBean {
        private var _count = 0
        private val log = LoggerFactory.getLogger(this::class.java)

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
