package project.swagger.singlemodule.common.exception

open class DomainException(
    val errorMessage: String,
    val code: Int
) : RuntimeException()
