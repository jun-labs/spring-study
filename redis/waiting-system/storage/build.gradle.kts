configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // UUID
    api("com.fasterxml.uuid:java-uuid-generator:4.0.1")

    // MySQL
    implementation("mysql:mysql-connector-java:8.0.28")

    // MongoDB
    api("org.springframework.boot:spring-boot-starter-data-mongodb")
    api("org.springframework.data:spring-data-relational:3.0.6")

    // Redis
    api("org.springframework.boot:spring-boot-starter-data-redis")
    api("it.ozimov:embedded-redis:0.7.2")
}
