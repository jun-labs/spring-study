package project.study.event.common.exception.utils

import project.study.event.common.exception.BaseTypeException
import project.study.event.common.exception.ProjectException

fun fail(baseTypeException: BaseTypeException): Nothing {
    throw ProjectException(baseTypeException)
}
