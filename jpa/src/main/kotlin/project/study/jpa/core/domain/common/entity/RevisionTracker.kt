package project.study.jpa.core.domain.common.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp
import java.io.Serializable

@RevisionEntity
@Entity(name = "revision_tracking")
class RevisionTracker(

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var rev: Long? = null,

    @RevisionTimestamp
    private var createdAt: Long
) : Serializable {

    fun getRev(): Long? {
        return rev
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RevisionTracker) return false
        if (rev != other.rev) return false
        return true
    }

    override fun hashCode(): Int {
        return rev?.hashCode() ?: 0
    }

    override fun toString(): String {
        return rev.toString()
    }
}
