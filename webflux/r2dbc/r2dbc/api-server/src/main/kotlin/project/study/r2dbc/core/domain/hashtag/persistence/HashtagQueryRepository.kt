package project.study.r2dbc.core.domain.hashtag.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.hashtag.ClubHashtag
import reactor.core.publisher.Mono

interface HashtagQueryRepository : ReactiveCrudRepository<ClubHashtag, Long> {
    @Query(value = "SELECT * FROM club_hashtag AS ch JOIN club c on c.club_id = ch.club_id WHERE ch.club_id = :clubId")
    fun findTestById(clubId: Long): Mono<ClubHashtag>?

}
