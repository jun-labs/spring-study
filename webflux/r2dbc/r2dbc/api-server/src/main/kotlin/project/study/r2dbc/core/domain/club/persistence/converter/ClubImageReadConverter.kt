package project.study.r2dbc.core.domain.club.persistence.converter

import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.club.Club
import project.study.r2dbc.core.domain.club.ClubImage
import java.time.LocalDateTime
import java.time.ZoneOffset

@ReadingConverter
class ClubImageReadConverter : Converter<Row, ClubImage> {

    override fun convert(source: Row): ClubImage? {
        val club = Club(
            clubId = source["club_id"]?.let { it as Long },
            userId = source["user_id"] as Long,
            title = source["title"] as String,
            content = source["content"] as String,
            logoImageUrl = source["logo_image_url"] as String,
            memberCount = (source["member_count"] as Number).toInt(),
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String)
        )

        return ClubImage(
            clubImageId = source["club_image_id"]?.let { it as Long },
            clubId = source["club_id"] as Long,
            imageUrl = source["image_url"] as String,
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String),
            club = club
        )
    }
}
