package project.study.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class SpringCloudApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudApplication>(*args)
}
