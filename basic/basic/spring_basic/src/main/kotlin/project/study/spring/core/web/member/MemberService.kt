package project.study.spring.core.web.member

import project.study.spring.core.domain.member.Member

interface MemberService {

    fun save(member: Member): Member

    fun findById(memberId: Long): Member?
}
