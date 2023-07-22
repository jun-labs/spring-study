package project.study.waiting.core.order.application.out.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import project.study.waiting.core.order.application.out.EventSendPort
import project.study.waiting.core.order.event.OrderCreateEvent

@Component
class EventAdapter(
    @Value("\${spring.kafka.topic}")
    private val topic: String,

    @Value("\${spring.kafka.key}")
    private val key: String,

    val kafkaTemplate: KafkaTemplate<String, OrderCreateEvent>
) : EventSendPort {

    override fun send(event: OrderCreateEvent) {
        try {
            kafkaTemplate.send(topic, key, event)
        } catch (ex: Exception) {
            throw RuntimeException();
        } finally {

        }
    }
}
