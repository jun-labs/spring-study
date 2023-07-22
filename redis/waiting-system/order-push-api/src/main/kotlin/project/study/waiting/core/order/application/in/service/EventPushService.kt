package project.study.waiting.core.order.application.`in`.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.study.waiting.common.core.order.OrderEvent
import project.study.waiting.core.order.application.`in`.EventPushUseCase
import project.study.waiting.core.order.application.out.EventPersistPort
import project.study.waiting.core.order.application.out.EventPushPort
import project.study.waiting.core.order.application.out.EventSendPort
import project.study.waiting.core.order.event.OrderCreateEvent

@Service
class EventPushService(
    private val eventPushPort: EventPushPort,
    private val eventPersistPort: EventPersistPort,
    private val eventSendPort: EventSendPort
) : EventPushUseCase {

    @Transactional
    override fun send(orderEvent: OrderEvent): String {
        eventPushPort.push(orderEvent)
        val newOrderEvent = eventPersistPort.save(orderEvent)
        val event = OrderCreateEvent(newOrderEvent.id!!)
        eventSendPort.send(event)
        return newOrderEvent.id!!
    }
}
