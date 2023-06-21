package project.study.event.test.post

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import project.study.event.common.PersistenceHelper
import project.study.event.core.domain.bookmark.entity.Bookmark
import project.study.event.core.domain.comment.entity.Comment
import project.study.event.core.domain.post.entity.Post
import project.study.event.core.web.post.application.PostCommandService

@SpringBootTest
@ActiveProfiles("test")
class PostDeleteIntegrationTest {

    @Autowired
    private lateinit var persistenceHelper: PersistenceHelper

    @Autowired
    private lateinit var postCommandService: PostCommandService

    @Test
    @DisplayName("게시글을 삭제하면 모든 북마크가 삭제된다.")
    fun when_delete_post_then_all_bookmarks_should_be_deleted() {
        val newPost = given()
        postCommandService.publishEvent(newPost.postId!!)

        val findBookmarks = persistenceHelper.findBookmarksById(newPost.postId!!)
        val findComments = persistenceHelper.findCommentsById(newPost.postId!!)

        assertEquals(0, findBookmarks.size)
        assertEquals(0, findComments.size)
    }

    private fun given(): Post {
        val newPost = persistenceHelper.persist(Post(null, "Hello", "World"))
        persistenceHelper.persist(Bookmark(null, newPost))
        persistenceHelper.persist(Bookmark(null, newPost))
        persistenceHelper.persist(Comment(null, 1L, newPost.postId!!, "Hello"))
        persistenceHelper.persist(Comment(null, 1L, newPost.postId!!, "Hello"))
        return newPost
    }
}
