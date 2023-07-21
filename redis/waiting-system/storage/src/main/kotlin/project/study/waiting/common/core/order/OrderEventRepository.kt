package project.study.waiting.common.core.order

import org.springframework.data.mongodb.repository.MongoRepository

interface OrderEventRepository : MongoRepository<OrderEvent, String>
