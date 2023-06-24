package project.study.spring.core.domain.order

import java.math.BigDecimal
import java.util.*

class Order(
    val memberId: Long,
    val itemName: String,
    val price: BigDecimal,
    val discountPrice: BigDecimal
) {
    private val orderId: UUID = UUID.randomUUID()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Order) return false
        if (orderId != other.orderId) return false
        return true
    }

    override fun hashCode(): Int {
        return orderId.hashCode()
    }

    override fun toString(): String {
        return orderId.toString()
    }
}
