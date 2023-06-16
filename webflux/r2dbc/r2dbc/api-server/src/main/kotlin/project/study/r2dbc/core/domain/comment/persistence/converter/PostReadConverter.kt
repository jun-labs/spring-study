package project.study.r2dbc.core.domain.comment.persistence.converter

import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import project.study.r2dbc.core.common.field.Deleted
import project.study.r2dbc.core.domain.comment.Comment
import project.study.r2dbc.core.domain.post.Post
import java.time.LocalDateTime
import java.time.ZoneOffset

@ReadingConverter
class PostReadConverter : Converter<Row, Comment> {

    override fun convert(source: Row): Comment? {
        val post = Post(
            postId = source["post_id"]?.let { it as Long },
            userId = source["user_id"] as Long,
            clubId = source["club_id"] as Long,
            content = source["content"] as String,
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String)
        )
        return Comment(
            commentId = source["comment_id"]?.let { it as Long },
            userId = source["user_id"] as Long,
            postId = source["post_id"] as Long,
            content = source["content"] as String,
            createdBy = (source["created_by"] as Number).toLong(),
            createdAt = (source["created_at"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            lastModifiedBy = source["last_modified_by"]?.let { it as Long },
            lastModifiedAt = source["last_modified_at"]?.let { it as LocalDateTime }?.toInstant(ZoneOffset.UTC),
            deleted = Deleted.getDeleted(source["deleted"] as String),
            post = post
        )
    }
}
