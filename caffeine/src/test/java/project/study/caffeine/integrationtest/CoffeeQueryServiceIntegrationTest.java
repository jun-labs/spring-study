package project.study.caffeine.integrationtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import project.study.caffeine.core.model.Coffee;
import project.study.caffeine.core.web.application.CoffeeQueryService;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("[IntegrationTest] 커피 메뉴 리스트 조회 통합 테스트")
class CoffeeQueryServiceIntegrationTest {

    @SpyBean
    private CoffeeQueryService coffeeQueryService;

    @Test
    @DisplayName("@Cacheable이 적용 돼 있다면 메서드는 실제 호출되지 않는다.")
    void when_adjust_cacheable_annotation_then_method_not_called() throws Exception {
        List<Coffee> expected = List.of(Coffee.AMERICANO, Coffee.LATTE);
        doReturn(expected)
                .when(coffeeQueryService).getCoffeeMenu();

        verify(coffeeQueryService, times(0)).getCoffeeMenu();
    }
}
