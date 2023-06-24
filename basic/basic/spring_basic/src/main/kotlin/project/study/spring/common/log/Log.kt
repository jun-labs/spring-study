package project.study.spring.common.log

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.*

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class Log {
    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var uuid: String
    private lateinit var message: String

    fun registerMessage(message: String) {
        this.message = message
    }

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("INIT: $this")
        log.info("------------------------------------------------")
    }

    @PreDestroy
    fun close() {
        log.info("DESTROY: $this")
        log.info("------------------------------------------------")
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun toString(): String {
        return uuid
    }
}
