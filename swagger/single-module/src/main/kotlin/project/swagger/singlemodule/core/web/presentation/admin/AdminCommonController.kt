package project.swagger.singlemodule.core.web.presentation.admin

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "[Admin] Command",
    description = "AdminCommonController API"
)
@RestController
@RequestMapping("/api/admin/common")
class AdminCommonController {

    @PostMapping
    fun savePoint() {
    }
}
