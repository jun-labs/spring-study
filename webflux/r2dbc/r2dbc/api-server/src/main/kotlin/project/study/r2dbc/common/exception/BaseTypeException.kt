package project.study.r2dbc.common.exception

import org.springframework.http.HttpStatus

interface BaseTypeException {

    fun getCode(): Int

    fun getMessage(): String

    fun getStatus(): HttpStatus
}
