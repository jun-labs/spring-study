package project.study.r2dbc.core.domain.club.persistence.converter

import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.club.ClubUser
import project.study.r2dbc.core.domain.user.Role
import project.study.r2dbc.core.domain.user.User
import java.time.LocalDateTime
import java.time.ZoneOffset

@ReadingConverter
class ClubUserReadConverter : Converter<Row, ClubUser> {

    override fun convert(source: Row): ClubUser {
        val user = User(
            userId = source["user_id"]?.let { it as Long },
            nickname = source["nickname"]?.let { it as String },
            profileImageUrl = source["profile_image_url"]?.let { it as String },
            role = source["role"]?.let { it as String }?.let { Role.getRole(it) },
            deleted = source["deleted"]?.let { it as String }?.let { Deleted.getDeleted(it) }
        )
        return ClubUser(
            clubUserId = source["club_user_id"]?.let { it as Long },
            clubId = source["club_id"] as Long,
            userId = source["user_id"] as Long,
            createdBy = source["created_by"] as Long,
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String),
            user = user
        )
    }
}
