package project.study.r2dbc.core.domain.notice

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import project.study.r2dbc.core.common.field.Deleted
import java.time.Instant

@Table(name = "notice")
class Notice(
    @Id
    @Column(value = "notice_id")
    val noticeId: Long? = null,

    @Column(value = "user_id")
    val userId: Long? = null,

    @Column(value = "club_id")
    val clubId: Long? = null,

    @Column(value = "created_by")
    val createdBy: Long,

    @Column(value = "created_at")
    val createdAt: Instant,

    @Column(value = "last_modified_by")
    val lastModifiedBy: Long? = null,

    @Column(value = "last_modified_at")
    val lastModifiedAt: Instant? = null,

    @Column(value = "deleted")
    val deleted: Deleted
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Notice) return false
        if (noticeId != other.noticeId) return false
        return true
    }

    override fun hashCode(): Int {
        return noticeId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return noticeId.toString()
    }
}
