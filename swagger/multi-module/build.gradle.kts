import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val restdocsApiSpecVersion: String by project
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.epages.restdocs-api-spec")
}

allprojects {
    val projectGroup: String by project
    val applicationVersion: String by project

    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.epages.restdocs-api-spec")

    dependencyManagement {
        val springCloudDependenciesVersion: String by project
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudDependenciesVersion")
        }
    }

    dependencies {
        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // SpringBoot
        implementation("org.springframework.boot:spring-boot-starter-web")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
        testImplementation("com.epages:restdocs-api-spec-mockmvc:$restdocsApiSpecVersion")

        // Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor(
            "org.projectlombok:lombok",
            "org.springframework.boot:spring-boot-configuration-processor"
        )
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.register<Copy>("copyOpenApiSpecToStatic") {
        dependsOn("openapi3")
        from("${project.buildDir}/api-spec/openapi3.json")
        into("${project.projectDir}/src/main/resources/static/api-spec")
    }

    tasks.named("build").configure {
        dependsOn("copyOpenApiSpecToStatic")
    }
}

openapi3 {
    this.setServer("https://localhost:8080")
    title = "Swagger Sample API Documentation"
    description = "Swagger Sample API Documentation"
    version = "1.0.0"
    format = "json"
}
