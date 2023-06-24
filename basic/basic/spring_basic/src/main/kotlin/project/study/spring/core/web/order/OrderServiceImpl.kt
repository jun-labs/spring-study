package project.study.spring.core.web.order

import org.springframework.stereotype.Component
import project.study.spring.common.annotation.MainDiscountPolicy
import project.study.spring.core.domain.discount.DiscountPolicy
import project.study.spring.core.domain.member.persistence.MemberRepository
import project.study.spring.core.domain.order.Order
import java.math.BigDecimal

@Component
@MainDiscountPolicy
class OrderServiceImpl(
    val memberRepository: MemberRepository,
    val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = discountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
