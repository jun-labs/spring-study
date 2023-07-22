package project.study.waiting.core.order.presentation.request

import com.fasterxml.uuid.Generators
import project.study.waiting.common.core.order.OrderEvent
import project.study.waiting.core.user.User
import java.math.BigDecimal

data class OrderRequest(
    val productId: Long,
    val price: BigDecimal
) {
    fun toEvent(): OrderEvent {
        return OrderEvent(
            Generators.timeBasedGenerator().generate().toString(),
            User.USER_ID,
            productId,
            price
        )
    }

    override fun toString(): String {
        return "productId: $productId, price: $price"
    }
}
