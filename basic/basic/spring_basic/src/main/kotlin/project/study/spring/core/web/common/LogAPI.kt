package project.study.spring.core.web.common

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.study.spring.common.log.Log

@RestController
@RequestMapping("/api/query")
class LogAPI(
    private val myLogger: Log
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun callAPI() {
        log.info("LOGGER: $myLogger")
        log.info("PROXY: ${myLogger.javaClass}")
        myLogger.registerMessage("HELLO")
    }
}
