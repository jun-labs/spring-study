package project.study.waiting.core.order.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity(name = "coupon")
class Order(
    @Id
    val id: UUID,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "product_id")
    val productId: Long,

    @Column(name = "price")
    val price: BigDecimal
) {
    @Column(name = "domain")
    val domain: String = DOMAIN

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Order) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return id.toString()
    }

    companion object {
        private const val DOMAIN = "Order"
    }
}
