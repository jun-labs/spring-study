package project.study.jpa.core.domain.user.entity.values

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Embeddable
class AuditedColumns(

    @CreatedBy
    @Column(name = "created_by")
    private var createdBy: Long? = null,

    @Column(name = "created_at")
    @CreatedDate
    private val createdAt: Instant? = Instant.now(),

    @Column(name = "modified_by")
    @LastModifiedBy
    private var modifiedBy: Long? = null,

    @Column(name = "modified_at")
    @LastModifiedDate
    private var modifiedDate: Instant? = Instant.now()
) {

    fun getCreatedBy(): Long? {
        return createdBy
    }

    fun getCreatedAt(): Instant {
        return createdAt!!
    }

    fun getModifiedBy(): Long {
        return modifiedBy!!
    }

}
