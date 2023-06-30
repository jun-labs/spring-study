package project.study.spring.test.life_cycle

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifecycleTest {

    @Test
    @DisplayName("빈 라이프사이클 조회")
    fun print_bean_lifecycle() {
        val applicationContext = AnnotationConfigApplicationContext(LifeCycleConfiguration::class.java)
        val client = applicationContext.getBean(NetworkClient::class.java)
        client.close()
    }

    @Configuration
    class LifeCycleConfiguration {
        // @Bean(initMethod = "init", destroyMethod = "destroy")
        @Bean
        fun networkClient(): NetworkClient {
            val client = NetworkClient()
            client.registerUrl("http://hello-spring.dev")
            return client
        }
    }
}
