package project.study.waiting.core.order.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.study.waiting.core.order.application.`in`.EventPushUseCase
import project.study.waiting.core.order.presentation.request.OrderRequest

@RestController
@RequestMapping("/api/orders")
class OrderCreateAPI(
    val eventPushUseCase: EventPushUseCase
) {

    @PostMapping
    fun createOrder(
        @RequestBody request: OrderRequest
    ) {
        val orderId = eventPushUseCase.send(request.toEvent())
    }
}
