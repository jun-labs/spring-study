package project.study.spring.core.domain.member.persistence

import project.study.spring.core.domain.member.Member

interface MemberRepository {

    fun save(member: Member): Member

    fun findById(memberId: Long): Member?
}
