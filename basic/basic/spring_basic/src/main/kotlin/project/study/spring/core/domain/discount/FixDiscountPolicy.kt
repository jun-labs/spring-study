package project.study.spring.core.domain.discount

import org.springframework.stereotype.Component
import project.study.spring.core.domain.member.Grade
import project.study.spring.core.domain.member.Member
import java.math.BigDecimal

@Component
class FixDiscountPolicy : DiscountPolicy {

    override fun discount(
        member: Member,
        price: BigDecimal
    ): BigDecimal {
        if (member.grade == Grade.VIP) {
            return price.multiply(FIXED_DISCOUNT_PRICE)
        }
        return BigDecimal.ZERO
    }

    companion object {
        private val FIXED_DISCOUNT_PRICE = BigDecimal.valueOf(1000)
    }
}
