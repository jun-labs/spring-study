plugins {
    kotlin("plugin.jpa") version "1.7.22"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}
