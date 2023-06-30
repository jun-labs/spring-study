package project.study.spring.test.life_cycle

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory

class NetworkClient {

    private val log = LoggerFactory.getLogger(this::class.java)
    private var url: String? = null

    init {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        // connect()
        // call()
    }

    fun registerUrl(url: String) {
        this.url = url
    }

    private fun connect() {
        log.info("Connect")
    }

    private fun call() {
        log.info("Call")
    }

    fun close() {
        log.info("Close")
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    @PostConstruct
    fun init() {
        log.info("INIT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        connect()
        call()
    }

    @PreDestroy
    fun destroy() {
        close()
    }
}
