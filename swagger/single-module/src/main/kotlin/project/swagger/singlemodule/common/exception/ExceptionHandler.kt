package project.swagger.singlemodule.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(DomainException::class)
    fun resolveDomainException(exception: DomainException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(exception.code)
            .body(ErrorResponse(exception.errorMessage, exception.code))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun resolveMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse("BAD REQUEST", 400))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun resolveMissingServletRequestParameterException(
        exception: MissingServletRequestParameterException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse("BAD REQUEST", 400))
    }

    @ExceptionHandler(Exception::class)
    fun resolveException(exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(500)
            .body(ErrorResponse("INTERNAL SERVER ERROR", 500))
    }
}

data class ErrorResponse(
    val message: String,
    val code: Int
)
