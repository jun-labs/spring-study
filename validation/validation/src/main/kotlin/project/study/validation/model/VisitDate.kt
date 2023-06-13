package project.study.validation.model

import java.time.LocalDateTime

@JvmInline
value class VisitDate(
    val visitDate: LocalDateTime
) {

    override fun toString(): String {
        return visitDate.toString()
    }
}
