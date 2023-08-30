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
import project.swagger.singlemodule.core.web.presentation.UserResponse
import java.lang.annotation.Inherited

@Operation(
    summary = "User details searching by pk.",
    description = "API for searching user details by pk."
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
                            name = "Success Response",
                            value = """
{
    "id": 1,
    "name": "Jung"
}
""",
                            description = "Success response"
                        )
                    ],
                    schema = Schema(implementation = UserResponse::class),
                    schemaProperties = [
                        SchemaProperty(
                            name = "message",
                            schema = Schema(type = "Number", format = "Long")
                        ),
                        SchemaProperty(
                            name = "code",
                            schema = Schema(type = "String", format = "String"),

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
            responseCode = "404",
            description = "NOT FOUND",
            content = [
                Content(
                    examples = [
                        ExampleObject(
                            name = "Error response",
                            value = """
{
    "message": "NOT FOUND",
    "code": 404
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
        name = "id",
        description = "User unique id.",
        example = "1"
    )
)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class UserSearchByIdApiSpec()
