package project.study.jpa.core.web.city.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.study.jpa.core.web.city.application.DistrictQueryService
import project.study.jpa.core.web.city.presentation.response.DistrictResponse

@RestController
@RequestMapping("/api/districts")
class DistrictQueryAPI(private val districtQueryService: DistrictQueryService) {

    @GetMapping("/{districtId}")
    fun searchDistrictDetail(
        @PathVariable districtId: Long
    ): ResponseEntity<DistrictResponse> {
        val data = districtQueryService.searchDistrictById(districtId)
        return ResponseEntity.ok(data)
    }
}
