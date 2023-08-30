package project.swagger.singlemodule.common.configuration.annotation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import project.swagger.singlemodule.common.exception.ErrorResponse
import project.swagger.singlemodule.core.web.presentation.UserSignupRequest
import project.swagger.singlemodule.core.web.presentation.UserSignupResponse
import java.lang.annotation.Inherited

@Operation(
    summary = "User sign-up.",
    description = "API for user sign-up.",
    requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = [Content(schema = Schema(implementation = UserSignupRequest::class))]
    )
)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [
                Content(
                    examples = [
                        ExampleObject(
                            name = "Success response",
                            value = """
{
    "id": 1
}
""",
                            description = "Success response."
                        )
                    ],
                    schema = Schema(implementation = UserSignupResponse::class),
                    schemaProperties = [
                        SchemaProperty(
                            name = "id",
                            schema = Schema(type = "Number", format = "Long")
                        )
                    ]
                ),
            ]
        ),
        ApiResponse(
            responseCode = "400",
            description = "BAD REQUEST",
            content = [
                Content(
                    examples = [
                        ExampleObject(
                            name = "Error response",
                            value = """
{
    "message": "BAD REQUEST",
    "code": 400
}
""",
                            description = "Error response."
                        )
                    ],
                    schema = Schema(implementation = ErrorResponse::class),

                    schemaProperties = [
                        SchemaProperty(
                            name = "message",
                            schema = Schema(type = "String", format = "String")
                        ),
                        SchemaProperty(
                            name = "code",
                            schema = Schema(type = "Number", format = "Int"),

                            )
                    ]
                ),
            ]
        ),
        ApiResponse(
            responseCode = "500",
            description = "INTERNAL SERVER ERROR",
            content = [
                Content(
                    examples = [
                        ExampleObject(
                            name = "Error response",
                            value = """
{
    "message": "INTERNAL SERVER ERROR",
    "code": 500
}
""",
                            description = "Error response."
                        )
                    ],
                    schema = Schema(implementation = ErrorResponse::class),
                    schemaProperties = [
                        SchemaProperty(
                            name = "message",
                            schema = Schema(type = "String", format = "String")
                        ),
                        SchemaProperty(
                            name = "code",
                            schema = Schema(type = "Number", format = "Int"),
                        )
                    ]
                ),
            ]
        )
    ]
)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class UserSignupApiSpec
