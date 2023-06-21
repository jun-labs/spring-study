package project.study.event.core.domain.bookmark.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import project.study.event.core.domain.bookmark.entity.Bookmark

interface BookmarkRepository : JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM bookmark b WHERE b.post.postId = :postId")
    fun findAllBookmarksById(postId: Long): List<Bookmark>
}
