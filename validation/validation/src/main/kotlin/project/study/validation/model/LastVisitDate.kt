package project.study.validation.model

import java.time.Instant

@JvmInline
value class LastVisitDate(
    val lastVisitDate: Instant
) {

    override fun toString(): String {
        return lastVisitDate.toString()
    }
}
