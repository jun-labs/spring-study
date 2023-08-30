package project.swagger.singlemodule.core.web.application

import org.springframework.stereotype.Service
import project.swagger.singlemodule.core.domain.entity.User
import project.swagger.singlemodule.core.domain.repository.UserRepository
import project.swagger.singlemodule.core.web.exception.UserNotFoundException
import project.swagger.singlemodule.core.web.presentation.UserResponse

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun save(user: User): Long {
        val newUser = userRepository.save(user)
        return newUser.userId
    }

    fun findUserById(id: Long): UserResponse {
        val findUser = userRepository.findUserById(id)
            ?: throw UserNotFoundException()
        return UserResponse(findUser.userId, findUser.name)
    }

    fun findUserByName(name: String): UserResponse {
        val findUser = userRepository.findUserByName(name)
            ?: throw UserNotFoundException()
        return UserResponse(findUser.userId, findUser.name)
    }

    fun findAllUsers(): List<UserResponse> {
        return userRepository.findAllUser()
            .map { UserResponse(it.userId, it.name) }
            .toList()
    }
}
