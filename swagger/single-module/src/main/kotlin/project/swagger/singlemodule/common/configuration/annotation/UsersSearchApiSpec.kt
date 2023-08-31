package project.swagger.singlemodule.common.configuration.annotation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import project.swagger.singlemodule.common.exception.ErrorResponse
import project.swagger.singlemodule.core.web.presentation.UserResponses
import java.lang.annotation.Inherited

@Operation(
    summary = "User list searching.",
    description = "API for searching user list."
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
                            value = """users: [
    {
        "id": 1,
        "name": "John"
    },
    {
        "id": 2,
        "name": "Lina"
    }
]
""",
                            description = "Success response."
                        )
                    ],
                    schema = Schema(implementation = UserResponses::class),
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
@Parameters(
    Parameter(
        name = "index",
        description = "User's pk.",
        example = "11"
    ),
    Parameter(
        name = "limit",
        description = "User list size.",
        example = "10"
    )
)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
internal annotation class UsersSearchApiSpec
