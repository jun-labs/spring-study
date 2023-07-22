package project.study.waiting.core.order.application.out

import project.study.waiting.common.core.order.OrderEvent

interface EventPersistPort {
    fun save(orderEvent: OrderEvent): OrderEvent
}
