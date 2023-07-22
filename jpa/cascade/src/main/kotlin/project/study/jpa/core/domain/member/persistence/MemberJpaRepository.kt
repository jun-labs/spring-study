package project.study.jpa.core.domain.member.persistence

import org.springframework.data.jpa.repository.JpaRepository
import project.study.jpa.core.domain.member.entity.Member

interface MemberJpaRepository : JpaRepository<Member, Long>
