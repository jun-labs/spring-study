package project.study.jpa.core.domain.user.entity

import jakarta.persistence.*
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import project.study.jpa.core.domain.user.entity.values.AuditedColumns
import java.time.Instant

@Audited
@Entity(name = "user")
@EntityListeners(value = [AuditingEntityListener::class])
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var userId: Long? = null,

    @Column
    private var nickname: String,

    @Column
    private var weight: Double? = null,

    @Column
    private var phoneNumber: String? = null

) {
    @Audited
    @Embedded
    private var auditedColumns: AuditedColumns = AuditedColumns(
        userId,
        Instant.now(),
        userId,
        null
    )

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private var banned: Banned = Banned.FALSE

    fun getUserId(): Long? {
        return userId
    }

    fun getNickname(): String {
        return nickname
    }

    fun updateProfile(
        nickname: String,
        weight: Double
    ) {
        this.nickname = nickname
        this.weight = weight
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (userId != other.userId) return false
        return true
    }

    override fun hashCode(): Int {
        return userId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return userId.toString()
    }
}
