package project.study.waiting.core.order.application.out.adapter

import org.springframework.stereotype.Component
import project.study.waiting.common.core.order.OrderEvent
import project.study.waiting.common.core.order.OrderEventRepository
import project.study.waiting.core.order.application.out.EventPersistPort

@Component
class OrderAdapter(
    private val orderRepository: OrderEventRepository
) : EventPersistPort {

    override fun save(orderEvent: OrderEvent): OrderEvent {
        return orderRepository.save(orderEvent)
    }
}
