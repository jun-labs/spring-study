package project.study.waiting.common.configuration.jpa

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["project.study.waiting.core"])
@EnableJpaRepositories(basePackages = ["project.study.waiting.core"])
class MainJpaConfiguration
