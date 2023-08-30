package project.swagger.singlemodule.common.configuration.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.tags.Tag
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@Configuration
class SwaggerConfiguration {

    @Bean
    fun userGroup(): GroupedOpenApi {
        val adminTags = listOf<Tag>(
            Tag().name("UserQueryController")
                .description("<br>[User]</b> Common"),
            Tag().name("UserCommandController")
                .description("<br>[User]</b> Budget")
        )
        return GroupedOpenApi.builder()
            .group("User")
            .pathsToMatch("/api/users/**")
            .addOpenApiCustomizer {
                openAPI().tags = adminTags
            }
            .build()
    }

    @Bean
    fun adminGroup(): GroupedOpenApi {
        val adminTags = listOf<Tag>(
            Tag().name("AdminCommonController")
                .description("<br>[Admin]</b> Common"),
            Tag().name("AdminBudgetController")
                .description("<br>[Admin]</b> Budget")
        )
        return GroupedOpenApi.builder()
            .group("Admins")
            .pathsToMatch("/api/admin/**")
            .addOpenApiCustomizer {
                openAPI().tags = adminTags
            }
            .build()
    }

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(apiInfo())
        .components(Components().addSecuritySchemes("bearerAuth", securityScheme))
        .security(listOf(securityRequirement))

    val securityScheme: SecurityScheme = SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT")
        .`in`(SecurityScheme.In.HEADER)
        .name("Authorization")

    val securityRequirement: SecurityRequirement =
        SecurityRequirement().addList("bearerAuth")

    private fun apiInfo() = Info()
        .title("Spring-Docs with OpenAPI")
        .description("Swagger UI Sample")
        .contact(contact())
        .extensions(extraInformation())
        .version("1.0.0")
        .license(license())
        .termsOfService("https://example.com/terms-of-service")
        .summary("Swagger UI Sample")

    private fun license(): License? =
        License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")

    private fun extraInformation() = mapOf(
        "x-company-info" to mapOf("address" to "Hello-Street 101")
    )

    private fun contact(): Contact? =
        Contact()
            .name("Jun")
            .email("jun@example.com")
            .url("http://example.com")
}
