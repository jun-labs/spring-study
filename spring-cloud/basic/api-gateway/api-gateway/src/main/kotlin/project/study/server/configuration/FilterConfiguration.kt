package project.study.server.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfiguration(
    @Value("\${locations.first}") private val firstServer: String,
    @Value("\${locations.second}") private val secondServer: String
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route { r ->
            r.path("/api/clients/**").filters { f ->
                f.addRequestHeader("first", "first")
                    .addResponseHeader("first", "first")
            }.uri(firstServer)
        }
            .route { r ->
                r.path("/api/clients/**").filters { f ->
                    f.addRequestHeader("second", "second")
                        .addResponseHeader("second", "second")
                }.uri(secondServer)
            }
            .build()
    }
}
