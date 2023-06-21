package project.study.event.common.exception

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import project.study.event.common.exception.response.ErrorResponse

@Slf4j
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ProjectException::class)
    fun resolveJMException(
        projectException: ProjectException
    ): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.badRequest(projectException)
        return ResponseEntity.status(response.status)
            .body(response)
    }

    @ExceptionHandler(Exception::class)
    fun catchUnresolvedException(
        exception: Exception
    ): ResponseEntity<ErrorResponse> {
        val response = exception.message?.let { ErrorResponse.badRequest(it) }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
            .body(response)
    }
}
