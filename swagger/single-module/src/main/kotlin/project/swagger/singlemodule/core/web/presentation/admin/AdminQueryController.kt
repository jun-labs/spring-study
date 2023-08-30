package project.swagger.singlemodule.core.web.presentation.admin

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "[Admin] Query",
    description = "AdminBudgetController API"
)
@RestController
@RequestMapping("/api/admin/budget")
class AdminQueryController {

    @GetMapping
    fun savePoint() {
    }
}
