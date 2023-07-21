package project.study.waiting.common.core.order

import com.fasterxml.uuid.Generators
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.relational.core.mapping.Column
import java.math.BigDecimal

@Document(collection = "orders")
class OrderEvent(
    @Id
    val id: String? = Generators.timeBasedGenerator().generate().toString(),

    @Column(value = "user_id")
    val userId: Long,

    @Column(value = "product_id")
    val productId: Long,

    @Column(value = "price")
    val price: BigDecimal
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderEvent) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return id.toString()
    }
}
