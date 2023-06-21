package project.study.event.common.exception

import org.springframework.http.HttpStatus

class ProjectException(
    val baseTypeException: BaseTypeException
) : RuntimeException(baseTypeException.getMessage()) {

    fun getCode(): Int {
        return baseTypeException.getCode()
    }

    fun getErrorMessage(): String {
        return baseTypeException.getMessage()
    }

    fun getStatus(): HttpStatus {
        return baseTypeException.getStatus()
    }

    override fun toString(): String {
        return "$baseTypeException"
    }
}
