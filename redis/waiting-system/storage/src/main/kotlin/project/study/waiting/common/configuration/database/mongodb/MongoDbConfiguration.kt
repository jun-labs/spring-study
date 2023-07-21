package project.study.waiting.common.configuration.database.mongodb

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.index.Index
import java.util.concurrent.TimeUnit

@Configuration
class MongoDbConfiguration(
    @Value("\${spring.data.mongodb.connectioin.connectionString}")
    private val connectionString: String
) {

    @Bean
    fun mongoDatabaseFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(connectionString);
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoTemplate = MongoTemplate(mongoDatabaseFactory())
        val index = Index().on("createdAt", Sort.Direction.ASC)
            .expire(30, TimeUnit.DAYS)

        mongoTemplate.indexOps("orders")
            .ensureIndex(index)
        return mongoTemplate
    }
}
