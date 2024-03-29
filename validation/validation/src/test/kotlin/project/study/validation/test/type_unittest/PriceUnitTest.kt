package project.study.validation.test.type_unittest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.validation.model.Price
import java.math.BigDecimal

@DisplayName("[UnitTest] 가격 단위 테스트")
class PriceUnitTest {

    @Test
    @DisplayName("BigDecimal을 래핑해도 값을 비교할 수 있다.")
    fun wrapping_decimal_test() {
        val price = Price(BigDecimal.TEN)
        val otherPrice = Price(BigDecimal.valueOf(1000))

        assertEquals(price.price, BigDecimal.valueOf(10))
        assertNotEquals(price, otherPrice)
    }

    @Test
    @DisplayName("BigDecimal의 소숫점 자릿수를 지정하지 않으면 같은 값이어도 다른 결과가 나온다.")
    fun decimal_compare_to_decimal_point() {
        val priceA = BigDecimal(1000)
        val priceB = BigDecimal.valueOf(1000.00)

        assertNotEquals(priceA, priceB)
    }

    @Test
    @DisplayName("BigDecimal의 소숫점 자릿수를 지정하면 소숫점이 달라도 같다고 판단한다.")
    fun decimal_compare_to_decimal_point_with_scale() {
        val priceA = BigDecimal(1000).setScale(2)
        val priceB = BigDecimal.valueOf(1000.00).setScale(2)

        assertEquals(priceA, priceB)
    }

    @Test
    @DisplayName("CompareTo를 이용하면 Scale이 달라도 값을 정확하게 비교할 수 있다.")
    fun decimal_compare_using_compare_to_method() {
        val priceA = Price(BigDecimal(1000))
        val priceB = Price(BigDecimal.valueOf(1000.00).setScale(3))

        assertEquals(0, priceA.compareTo(priceB))
    }
}
