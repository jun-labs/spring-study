package project.study.r2dbc.common.exception

fun fail(baseTypeException: BaseTypeException): Nothing {
    throw MoimException(baseTypeException)
}
