package project.study.jpa.core.web.city.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.study.jpa.core.web.city.application.CityCommandService
import project.study.jpa.core.web.city.presentation.request.CityUpdateRequest

@RestController
@RequestMapping("/api/cities")
class CityCommandAPI(private val cityCommandService: CityCommandService) {

    @PutMapping("/{cityId}")
    fun updateCityInformation(
        @PathVariable cityId: Long,
        @RequestBody request: CityUpdateRequest
    ): ResponseEntity<Unit> {
        cityCommandService.updateCity(cityId, request.name)
        return ResponseEntity.ok()
            .build()
    }
}
