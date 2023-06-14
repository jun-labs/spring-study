dependencies {
    // SpringBoot
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")

    // R2DBC
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka")
}
