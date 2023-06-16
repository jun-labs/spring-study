package project.study.r2dbc.core.domain.club.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.club.ClubUser
import reactor.core.publisher.Mono

interface ClubUserQueryRepository : ReactiveCrudRepository<ClubUser, Long> {

    @Query(
        value = "SELECT * " +
            "FROM club_user AS cu " +
            "INNER JOIN user AS u " +
            "ON cu.user_id = u.user_id " +
            "WHERE cu.club_id = :clubId"
    )
    fun findTestById(clubId: Long): Mono<ClubUser>?
}
