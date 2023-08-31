package project.swagger.multimodule.core.web.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class ProductDetailController {

    @GetMapping
    fun findProductById(@RequestParam name: String): ResponseEntity<UserDetailResponse> {
        return ResponseEntity.ok(UserDetailResponse(1L, name))
    }
}

data class UserDetailResponse(
    val id: Long,
    val name: String
)
