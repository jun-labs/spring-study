package project.study.r2dbc.common.configuration.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.DialectResolver
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.core.DatabaseClient
import project.study.r2dbc.core.domain.club.persistence.converter.ClubImageReadConverter
import project.study.r2dbc.core.domain.club.persistence.converter.ClubUserReadConverter
import project.study.r2dbc.core.domain.post.persistence.converter.PostImageReadConverter
import project.study.r2dbc.core.domain.comment.persistence.converter.PostReadConverter
import project.study.r2dbc.core.domain.hashtag.persistence.converter.ClubHashtagReadConverter

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories(basePackages = ["project.study.r2dbc.common"])
class R2dbcConfiguration {

    @Bean
    fun r2dbcCustomConversions(databaseClient: DatabaseClient): R2dbcCustomConversions {
        val dialect = DialectResolver.getDialect(databaseClient.connectionFactory)
        val converters = ArrayList(dialect.converters)
        converters.addAll(R2dbcCustomConversions.STORE_CONVERTERS)
        converters.add(ClubUserReadConverter())
        converters.add(ClubImageReadConverter())
        converters.add((PostReadConverter()))
        converters.add(PostImageReadConverter())
        converters.add(ClubHashtagReadConverter())
        return R2dbcCustomConversions.of(dialect, converters)
    }
}
