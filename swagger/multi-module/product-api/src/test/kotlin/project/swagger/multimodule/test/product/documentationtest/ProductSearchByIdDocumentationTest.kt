package project.swagger.multimodule.test.product.documentationtest

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import project.swagger.multimodule.test.product.snippet.ProductSnippet.Companion.PRODUCT_SEARCH_BY_ID_HANDLER

@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension::class)
class ProductSearchByIdDocumentationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("상품이 존재하면 200 OK를 반환한다.")
    fun product_search_by_id_test() {
        mockMvc.perform(get("/api/products/{id}", 1))
            .andExpect(status().isOk)
            .andDo(PRODUCT_SEARCH_BY_ID_HANDLER)
    }
}
