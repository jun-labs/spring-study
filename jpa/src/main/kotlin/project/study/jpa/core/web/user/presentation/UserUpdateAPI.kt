package project.study.jpa.core.web.user.presentation

import org.springframework.web.bind.annotation.*
import project.study.jpa.core.web.user.application.UserUpdateService
import project.study.jpa.core.web.user.presentation.request.NicknameUpdateRequest

@RestController
@RequestMapping("/api/users")
class UserUpdateAPI(
    private val userUpdateService: UserUpdateService
) {

    @PostMapping("/{userId}")
    fun updateNickname(
        @PathVariable userId: Long,
        @RequestBody request: NicknameUpdateRequest
    ) {
        userUpdateService.updateNickname(
            userId,
            request.getNickname(),
            request.getWeight()
        )
    }
}
