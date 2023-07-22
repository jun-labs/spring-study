package project.study.waiting.core.order.application.out

import project.study.waiting.common.core.order.OrderEvent

interface EventPushPort {
    fun push(event: OrderEvent)
}
