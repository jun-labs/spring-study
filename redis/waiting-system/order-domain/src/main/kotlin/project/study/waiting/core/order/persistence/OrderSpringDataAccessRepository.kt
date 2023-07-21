package project.study.waiting.core.order.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.waiting.core.order.entity.Order

interface OrderSpringDataAccessRepository : JpaRepository<Order, Long>
