package project.study.r2dbc.common.exception

import org.springframework.http.HttpStatus

class MoimException(
    val baseTypeException: BaseTypeException
) : RuntimeException(baseTypeException.getMessage()) {

    val code: Int
        get() = baseTypeException.getCode()

    val status: HttpStatus
        get() = baseTypeException.getStatus()

    override fun toString(): String {
        return "MoinException[ $baseTypeException ]"
    }
}
