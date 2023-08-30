rootProject.name = "single-module"

pluginManagement {
    val springbootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val openApiVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springbootVersion)
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "io.kotest.kotest-runner-junit5" -> useVersion(kotestVersion)
                "io.kotest.kotest-assertions-core" -> useVersion(kotestVersion)
                "org.springdoc" -> useVersion(openApiVersion)
            }
        }
    }
}
