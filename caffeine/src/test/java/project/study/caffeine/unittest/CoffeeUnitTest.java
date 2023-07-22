package project.study.caffeine.unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.study.caffeine.core.model.Coffee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[UnitTest] 커피 단위 테스트")
class CoffeeUnitTest {

    /**
     * Values order is also important.
     */
    @Test
    @DisplayName("Enum 값들이 정의되어 있으면 리스트를 얻을 수 있다.")
    void when_define_enum_values_then_can_get_enumlist() {
        List<Coffee> expected = List.of(Coffee.AMERICANO, Coffee.LATTE);

        List<Coffee> coffeeList = Arrays.stream(Coffee.values())
                .collect(Collectors.toUnmodifiableList());

        assertEquals(expected, coffeeList);
    }
}
