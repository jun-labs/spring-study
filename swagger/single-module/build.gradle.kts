import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotestVersion: String by project
val openApiVersion: String by project
val projectGroup: String by project
val applicationVersion: String by project
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // SpringBoot
    implementation("org.springframework.boot:spring-boot-starter-web")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openApiVersion")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
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
