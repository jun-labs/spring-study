package project.study.jpa.core.web.city.application

import org.springframework.stereotype.Service
import project.study.jpa.core.web.city.presentation.response.EmployeeResponse
import project.study.jpa.core.domain.city.persistence.EmployeeQueryRepository

@Service
class EmployeeQueryService(private val employeeQueryRepository: EmployeeQueryRepository) {
    fun findEmployeeById(
        cityId: Long,
        employeeId: Long
    ): EmployeeResponse {
        return employeeQueryRepository.findEmployeeById(cityId, employeeId)
    }
}
