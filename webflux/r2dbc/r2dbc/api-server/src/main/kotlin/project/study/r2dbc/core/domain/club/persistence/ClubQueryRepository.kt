package project.study.r2dbc.core.domain.club.persistence

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import project.study.r2dbc.core.domain.club.Club

interface ClubQueryRepository : ReactiveCrudRepository<Club, Long>
