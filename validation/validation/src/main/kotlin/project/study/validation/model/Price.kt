package project.study.validation.model

import java.math.BigDecimal

@JvmInline
value class Price(
    val price: BigDecimal
) {

    override fun toString(): String {
        return "$price 원"
    }
}
