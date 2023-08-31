package project.swagger.multimodule.test.user.documentationtest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import project.swagger.multimodule.test.user.snippet.UserSnippet.Companion.USER_SEARCH_BY_NAME_HANDLER

@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension::class)
class UserSearchByNameDocumentationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun contextLoads() {
        mockMvc.perform(
            get("/api/users")
                .queryParam("name", "Jung")
        ).andExpect(status().isOk)
            .andDo(USER_SEARCH_BY_NAME_HANDLER)
    }
}
