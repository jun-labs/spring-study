package project.study.spring.test

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.lang.Nullable
import project.study.spring.core.domain.member.Member
import java.util.*

class AutowiredTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var applicationContext: AnnotationConfigApplicationContext

    @Test
    fun autowired() {
        applicationContext = AnnotationConfigApplicationContext(TestBean::class.java)
    }


    class TestBean {
        private val log = LoggerFactory.getLogger(this::class.java)

        /**
         *  Member는 @Bean이 아니기 때문에
         *  @Autowired(required = false)를 설정해야 합니다.
         * */
        @Autowired(required = false)
        fun noBeanA(member: Member) {
            log.info("NoBeanA: $member")
        }

        @Autowired
        fun noBeanB(@Nullable member: Member?) {
            log.info("NoBeanB: $member")
        }

        @Autowired
        fun noBeanC(optionalMember: Optional<Member>) {
            log.info("NoBeanB: $optionalMember")
        }
    }
}
