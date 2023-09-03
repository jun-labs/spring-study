package project.swagger.multimodule.core.web.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import project.swagger.multimodule.common.exception.InvalidParameterException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(InvalidParameterException::class)
    fun resolveIllegalArgumentException(
        exception: InvalidParameterException
    ): ResponseEntity<ErrorResponse> {
        val payload = ErrorResponse(400, "올바른 값을 입력해주세요")
        return ResponseEntity.status(400)
            .body(payload)
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String
)
