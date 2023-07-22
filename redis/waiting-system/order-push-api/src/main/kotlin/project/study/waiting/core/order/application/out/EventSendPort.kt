package project.study.waiting.core.order.application.out

import project.study.waiting.core.order.event.OrderCreateEvent

interface EventSendPort {
    fun send(event: OrderCreateEvent)
}
