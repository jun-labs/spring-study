package project.study.waiting.core.order.event

class OrderCreateEvent(
    val id: String
) {
    override fun toString(): String {
        return id
    }
}
