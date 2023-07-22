package project.study.jpa.core.web.city.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.study.jpa.core.web.city.presentation.response.EmployeeResponse
import project.study.jpa.core.web.city.application.EmployeeQueryService

@RestController
@RequestMapping("/api/cities")
class CityQueryAPI(private val employeeQueryService: EmployeeQueryService) {

    @GetMapping("/{cityId}/employees/{employeeId}")
    fun searchEmployeeById(
        @PathVariable cityId: Long,
        @PathVariable employeeId: Long
    ): ResponseEntity<EmployeeResponse> {
        val body = employeeQueryService.findEmployeeById(cityId, employeeId)
        return ResponseEntity.ok(body)
    }
}
