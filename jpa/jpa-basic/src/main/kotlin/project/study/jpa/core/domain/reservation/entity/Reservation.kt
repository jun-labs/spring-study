package project.study.jpa.core.domain.reservation.entity

import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var reservationId: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", updatable = false)
    private val createdAt: Instant? = Instant.now(),

    @Temporal(TemporalType.TIME)
    @Column(name = "firstModifiedAt")
    private val firstModifiedAt: Date? = Date(),

    @Temporal(TemporalType.DATE)
    @Column(name = "secondModifiedAt")
    private val secondModifiedAt: Instant? = Instant.now(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thirdModifiedAt", columnDefinition = "TIMESTAMP")
    private val thirdModifiedAt: Instant? = Instant.now(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fourthModifiedAt", updatable = false)
    private val fourthModifiedAt: LocalDateTime? = LocalDateTime.now(),

    @Temporal(TemporalType.DATE)
    @Column(name = "lastModifiedAt")
    private val lastModifiedAt: Date? = Date()
) {
}
