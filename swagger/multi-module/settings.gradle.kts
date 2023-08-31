rootProject.name = "multi-module"

include(
    "product-api",
    "user-api",
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val asciidoctorVersion: String by settings
    val restdocsApiSpecVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.epages.restdocs-api-spec" -> useVersion(restdocsApiSpecVersion)
                "com.epages:restdocs-api-spec-restassured" -> useVersion(restdocsApiSpecVersion)
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.asciidoctor.jvm.convert" -> useVersion(asciidoctorVersion)
            }
        }
    }
}
