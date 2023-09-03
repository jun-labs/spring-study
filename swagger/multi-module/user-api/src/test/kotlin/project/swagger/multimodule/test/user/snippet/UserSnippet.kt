package project.swagger.multimodule.test.user.snippet

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName

interface UserSnippet {
    companion object {
        private const val IDENTIFIER: String = "{class-name}/{method-name}"

        private val USER_SEARCH_BY_NAME_QUERY_PARAMETERS =
            parameterWithName("name").description("User name.")

        private val USER_SEARCH_BY_NAME_RESPONSE_FIELD_DESCRIPTORS = listOf(
            fieldWithPath("id").type(JsonFieldType.NUMBER)
                .description("User id."),
            fieldWithPath("name").type(JsonFieldType.STRING)
                .description("User name.")
        )

        private val USER_SEARCH_BY_NAME_BAD_REQUEST_RESPONSE_FIELD_DESCRIPTORS = listOf(
            fieldWithPath("code").type(JsonFieldType.NUMBER)
                .description("Error code."),
            fieldWithPath("message").type(JsonFieldType.STRING)
                .description("Error message.")
        )

        val USER_SEARCH_BY_NAME_HANDLER = document(
            IDENTIFIER,
            snippets = arrayOf(
                ResourceDocumentation.resource(
                    ResourceSnippetParameters.builder()
                        .summary("User details searching.")
                        .description("API for searching user details by name.")
                        .tags("User")
                        .queryParameters(USER_SEARCH_BY_NAME_QUERY_PARAMETERS)
                        .responseFields(USER_SEARCH_BY_NAME_RESPONSE_FIELD_DESCRIPTORS)
                        .responseSchema(Schema.schema("test"))
                        .build()
                )
            )
        )

        val USER_SEARCH_BY_NAME_BAD_REQUEST_HANDLER = document(
            IDENTIFIER,
            snippets = arrayOf(
                ResourceDocumentation.resource(
                    ResourceSnippetParameters.builder()
                        .summary("User details searching bad-request.")
                        .description("API for searching user details by name badrequest.")
                        .tags("User")
                        .queryParameters(USER_SEARCH_BY_NAME_QUERY_PARAMETERS)
                        .responseFields(USER_SEARCH_BY_NAME_BAD_REQUEST_RESPONSE_FIELD_DESCRIPTORS)
                        .build()
                )
            )
        )
    }
}
