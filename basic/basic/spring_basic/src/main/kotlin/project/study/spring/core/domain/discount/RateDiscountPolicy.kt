package project.study.spring.core.domain.discount

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import project.study.spring.core.domain.member.Grade
import project.study.spring.core.domain.member.Member
import java.math.BigDecimal

@Primary
@Component
class RateDiscountPolicy : DiscountPolicy {

    override fun discount(
        member: Member,
        price: BigDecimal
    ): BigDecimal {
        if (member.grade == Grade.VIP) {
            return price.multiply(DISCOUNT_PERCENT)
        }
        return BigDecimal.ZERO
    }

    companion object {
        private val DISCOUNT_PERCENT = BigDecimal.valueOf(0.9)
    }
}
