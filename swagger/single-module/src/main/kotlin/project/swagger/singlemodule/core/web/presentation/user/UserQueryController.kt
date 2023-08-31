package project.swagger.singlemodule.core.web.presentation

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.swagger.singlemodule.common.configuration.annotation.UserSearchByIdApiSpec
import project.swagger.singlemodule.common.configuration.annotation.UserSearchByNameApiSpec
import project.swagger.singlemodule.common.configuration.annotation.UsersSearchApiSpec
import project.swagger.singlemodule.core.web.application.UserService

@RestController
@Tag(
    name = "[User] Query",
    description = "User Query API"
)
@RequestMapping("/api/users")
class UserQueryController(
    private val userService: UserService
) {

    @GetMapping("/{id}")
    @UserSearchByIdApiSpec
    fun findUserById(
        @PathVariable id: Long,
    ): ResponseEntity<UserResponse> {
        val payload = userService.findUserById(id)
        return ResponseEntity.ok(payload)
    }

    @GetMapping
    @UserSearchByNameApiSpec
    fun findUserByName(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @RequestParam("name") name: String,
    ): ResponseEntity<UserResponse> {
        val payload = userService.findUserByName(name)
        return ResponseEntity.ok(payload)
    }

    @GetMapping("/list")
    @UsersSearchApiSpec
    fun findAllUsers(
        @RequestParam("index") index: Long,
        @RequestParam("limit") limit: Int
    ): ResponseEntity<UserResponses> {
        val payload = UserResponses(userService.findAllUsers())
        return ResponseEntity.ok(payload)
    }
}

data class UserResponses(
    val users: List<UserResponse>
)

data class UserResponse(
    val id: Long,
    val name: String
)
