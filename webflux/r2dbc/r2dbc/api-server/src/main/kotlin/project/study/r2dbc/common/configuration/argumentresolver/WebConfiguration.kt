package project.study.r2dbc.common.configuration.argumentresolver

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer
import project.study.r2dbc.common.auth.LoginArgumentResolver
import project.study.r2dbc.core.domain.user.persistence.UserQueryRepository

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
class WebConfiguration(
    private val userQueryRepository: UserQueryRepository
) : WebFluxConfigurer {

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(LoginArgumentResolver(userQueryRepository))
    }
}
