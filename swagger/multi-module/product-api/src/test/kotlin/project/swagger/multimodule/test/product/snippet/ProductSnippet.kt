package project.swagger.multimodule.test.product.snippet

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ParameterDescriptorWithType
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.SimpleType
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.request.RequestDocumentation

interface ProductSnippet {

    companion object {
        private const val IDENTIFIER: String = "{class-name}/{method-name}"

        private val PRODUCT_SEARCH_BY_ID_PATH_PARAMETER: ParameterDescriptorWithType =
            ParameterDescriptorWithType.fromParameterDescriptor(
                RequestDocumentation.parameterWithName("id")
                    .description("Product id.")
            ).type(SimpleType.NUMBER)

        private val PRODUCT_SEARCH_BY_ID_RESPONSE_FIELD_DESCRIPTORS = listOf(
            fieldWithPath("id").type(JsonFieldType.NUMBER)
                .description("Product id."),
            fieldWithPath("name").type(JsonFieldType.STRING)
                .description("Product name.")
        )

        val PRODUCT_SEARCH_BY_ID_HANDLER = document(
            IDENTIFIER,
            snippets = arrayOf(
                ResourceDocumentation.resource(
                    ResourceSnippetParameters.builder()
                        .summary("Product details searching.")
                        .description("API for searching product details by id.")
                        .tags("Product")
                        .pathParameters(PRODUCT_SEARCH_BY_ID_PATH_PARAMETER)
                        .responseFields(PRODUCT_SEARCH_BY_ID_RESPONSE_FIELD_DESCRIPTORS)
                        .build()
                )
            )
        )
    }
}
