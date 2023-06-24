package project.study.spring.core.web.member

import org.springframework.stereotype.Component
import project.study.spring.core.domain.member.Member
import project.study.spring.core.domain.member.persistence.MemberRepository

@Component
class MemberServiceImpl(
    val memberRepository: MemberRepository
) : MemberService {

    override fun save(member: Member): Member {
        return memberRepository.save(member)
    }

    override fun findById(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}
