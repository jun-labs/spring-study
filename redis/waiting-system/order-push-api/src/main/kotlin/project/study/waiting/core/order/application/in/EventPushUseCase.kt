package project.study.waiting.core.order.application.`in`

import project.study.waiting.common.core.order.OrderEvent

interface EventPushUseCase {
    fun send(orderEvent: OrderEvent): String
}
