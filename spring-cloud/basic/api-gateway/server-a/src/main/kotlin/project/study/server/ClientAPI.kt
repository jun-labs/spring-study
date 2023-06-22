package project.study.server

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/clients/a")
class ClientAPI {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun callAPI(@RequestHeader first: String) {
        log.info(">>>>>>>> ServerA")
        log.info("$first")
    }
}
