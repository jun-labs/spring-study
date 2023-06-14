package project.study.apiserver

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping("/api/query")
class HealthCheckAPI(
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/health-check")
    fun healthCheck(
    ) {
        log.info("Health Check")
    }

    @GetMapping
    fun healthCheckV2(
    ) {
        log.info("Root Check")
    }
}
