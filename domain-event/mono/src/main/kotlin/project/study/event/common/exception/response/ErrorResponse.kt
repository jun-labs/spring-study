package project.study.event.common.exception.response

import org.springframework.http.HttpStatus
import project.study.event.common.exception.BaseTypeException
import project.study.event.common.exception.ProjectException

data class ErrorResponse private constructor(
    val code: Int,
    val message: String,
    val status: HttpStatus
) {

    private constructor(baseTypeException: BaseTypeException) : this(
        baseTypeException.getCode(),
        baseTypeException.getMessage(),
        baseTypeException.getStatus()
    )

    override fun toString(): String {
        return """
            Code: $code,
            Message: $message,
            Status: $status
        """.trimIndent()
    }

    companion object {
        fun badRequest(projectException: ProjectException): ErrorResponse {
            return ErrorResponse(projectException.baseTypeException)
        }

        fun of(
            code: Int,
            message: String,
            status: HttpStatus
        ): ErrorResponse {
            return ErrorResponse(
                code,
                message,
                status
            )
        }

        fun badRequest(message: String): ErrorResponse {
            return ErrorResponse(
                400,
                message,
                HttpStatus.BAD_REQUEST
            )
        }
    }
}
