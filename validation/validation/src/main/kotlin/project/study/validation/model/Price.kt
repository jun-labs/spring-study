package project.study.validation.model

import java.math.BigDecimal

@JvmInline
value class Price(
    val price: BigDecimal
) {

    fun compareTo(price: Price): Int {
        return this.price.compareTo(price.price)
    }

    override fun toString(): String {
        return "$price 원"
    }
}
