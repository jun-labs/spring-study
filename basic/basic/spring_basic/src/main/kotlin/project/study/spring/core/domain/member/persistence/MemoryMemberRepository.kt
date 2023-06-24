package project.study.spring.core.domain.member.persistence

import org.springframework.stereotype.Component
import project.study.spring.core.domain.member.Member

@Component
class MemoryMemberRepository : MemberRepository {

    private val store = HashMap<Long, Member>()

    override fun save(member: Member): Member {
        store[member.memberId] = member
        return member
    }

    override fun findById(memberId: Long): Member? {
        return store.get(memberId)
    }
}
