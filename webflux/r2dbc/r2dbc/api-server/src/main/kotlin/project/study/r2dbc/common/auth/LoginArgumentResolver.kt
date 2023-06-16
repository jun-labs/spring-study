package project.study.r2dbc.common.auth

import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import project.study.r2dbc.common.annotation.LoginUser
import project.study.r2dbc.common.exception.MoimException
import project.study.r2dbc.common.exception.fail
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.user.User
import project.study.r2dbc.core.domain.user.persistence.UserQueryRepository
import project.study.r2dbc.core.web.user.application.exception.UnAuthorizedException
import project.study.r2dbc.core.web.user.application.exception.UserNotFoundException
import reactor.core.publisher.Mono

@Component
class LoginArgumentResolver(
    private val userRepository: UserQueryRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> {
        val userId = exchange.request.headers.getFirst("authentication")

        // TODO. runBlocking 수정
        val findUser = runBlocking { findUserById(parseLong(userId)) }
        return Mono.fromCallable { MoimUser(findUser) }
    }

    fun parseLong(userId: String?): Long {
        if (userId != null) {
            return userId.toLong()
        }
        throw MoimException(UnAuthorizedException())
    }

    suspend fun findUserById(userId: Long): User {
        return userRepository.findUserById(1, Deleted.FALSE)?.awaitSingle()
            ?: fail(UserNotFoundException())
    }
}
