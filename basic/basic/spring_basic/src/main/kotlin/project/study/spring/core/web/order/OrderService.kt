package project.study.spring.core.web.order

import project.study.spring.core.domain.order.Order
import java.math.BigDecimal

interface OrderService {

    fun createOrder(memberId: Long, itemName: String, price: BigDecimal): Order
}
