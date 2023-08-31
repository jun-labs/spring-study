package project.swagger.singlemodule.core.web.presentation

import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.swagger.singlemodule.common.configuration.annotation.UserSignupApiSpec
import project.swagger.singlemodule.core.domain.entity.User
import project.swagger.singlemodule.core.web.application.UserService

@RestController
@Tag(
    name = "[User] Command",
    description = "User Command API"
)
@RequestMapping("/api/users")
class UserCommandController(
    private val userService: UserService
) {

    @PostMapping
    @UserSignupApiSpec
    fun signup(@RequestBody request: UserSignupRequest): ResponseEntity<UserSignupResponse> {
        val newUserId = userService.save(request.toEntity())
        return ResponseEntity.ok(UserSignupResponse(newUserId))
    }
}

data class UserSignupRequest(
    @field:Schema(
        required = true,
        description = "Unique identifier of the user.",
        example = "1"
    )
    val id: Long,

    @field:Schema(
        required = true,
        description = "Name of the user.",
        example = "John",
        maxLength = 10,
        pattern = "^\\S+$"
    )
    val name: String
) {
    fun toEntity(): User {
        return User(id, name)
    }
}

data class UserSignupResponse(
    val id: Long
)
