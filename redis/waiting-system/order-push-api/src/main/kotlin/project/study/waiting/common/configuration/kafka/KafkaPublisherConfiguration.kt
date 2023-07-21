package project.study.waiting.common.configuration.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import project.study.waiting.core.order.event.OrderCreateEvent

@Configuration
class KafkaPublisherConfiguration(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val serverIpAddress: String
) {

    @Bean
    fun messageProducerFactory(): ProducerFactory<String, OrderCreateEvent> {
        val configuration = HashMap<String, Any>()
        configuration[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = serverIpAddress
        configuration[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configuration[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configuration)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, OrderCreateEvent> {
        return KafkaTemplate(messageProducerFactory())
    }
}
