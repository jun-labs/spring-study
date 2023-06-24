package project.study.spring.core.domain.discount

import project.study.spring.core.domain.member.Member
import java.math.BigDecimal

interface DiscountPolicy {

    fun discount(member: Member, price: BigDecimal): BigDecimal
}
