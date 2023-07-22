package project.study.jpa.core.domain.common.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.history.RevisionRepository
import project.study.jpa.core.domain.common.entity.RevisionTracker

interface CommonRepository : JpaRepository<RevisionTracker, Long>, RevisionRepository<RevisionTracker, Long, Long> {
}
