package project.study.spring.test.life_cycle

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean


class NetworkClientV1 : InitializingBean, DisposableBean {

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

    override fun afterPropertiesSet() {
        log.info("INIT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        connect()
        call()
    }

    override fun destroy() {
        close()
    }
}
