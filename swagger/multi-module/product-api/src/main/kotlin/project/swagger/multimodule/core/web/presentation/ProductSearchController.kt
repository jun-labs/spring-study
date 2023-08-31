package project.swagger.multimodule.core.web.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductDetailController {

    @GetMapping("/{id}")
    fun findProductById(@PathVariable id: Long): ResponseEntity<ProductDetailResponse> {
        return ResponseEntity.ok(ProductDetailResponse(id, "PUMA"))
    }
}

data class ProductDetailResponse(
    val id: Long,
    val name: String
)
