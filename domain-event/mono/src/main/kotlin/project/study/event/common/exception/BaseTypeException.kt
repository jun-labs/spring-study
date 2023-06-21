package project.study.event.common.exception

import org.springframework.http.HttpStatus

interface BaseTypeException {

    fun getCode(): Int

    fun getMessage(): String

    fun getStatus(): HttpStatus
}
