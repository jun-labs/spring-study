package project.study.springcloud.common.configuration.cloud

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfiguration {

    @Value("\${api.path}")
    private lateinit var path: String

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { router ->
                router.path("/api/*")
                    .uri(path)
            }
            .build()
    }
}
