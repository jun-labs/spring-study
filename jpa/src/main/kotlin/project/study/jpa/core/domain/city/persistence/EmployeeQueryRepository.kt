package project.study.jpa.core.domain.city.persistence

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import project.study.jpa.core.web.city.presentation.response.EmployeeResponse
import project.study.jpa.core.domain.city.entity.QCity.Companion.city
import project.study.jpa.core.domain.city.entity.QEmployee.Companion.employee

@Repository
class EmployeeQueryRepository(private val queryFactory: JPAQueryFactory) {

    fun findEmployeeById(
        cityId: Long,
        employeeId: Long
    ): EmployeeResponse {
        return queryFactory.select(
            constructor(
                EmployeeResponse::class.java,
                employee.employeeId,
                employee.name,
                city.name
            )
        ).from(employee)
            .join(city)
            .on(city.cityId.eq(cityId))
            .where(employee.employeeId.eq(employeeId))
            .fetchOne()!!
    }
}
