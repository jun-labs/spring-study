package project.study.jpa.test.member

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import project.study.jpa.core.domain.member.entity.Member
import project.study.jpa.core.domain.member.entity.Team
import project.study.jpa.core.domain.member.persistence.MemberJpaRepository
import project.study.jpa.core.domain.member.persistence.TeamJpaRepository

@SpringBootTest
@ActiveProfiles("test")
class MemberCascadeTest {

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var teamJpaRepository: TeamJpaRepository

    @BeforeEach
    fun init() {
        memberJpaRepository.deleteAll()
        teamJpaRepository.deleteAll()
    }

    @Test
    @DisplayName("orphanRemoval 옵션 없이 CascadeType만 Remove라면 연관관계를 끊어도 자식이 삭제되지 않는다.")
    fun cascade_remove_test() {
        val messi = Member(null, "Messi")
        val xavi = Member(null, "Xavi")
        val team = Team(null, "FC Barcelona")

        team.register(mutableListOf(messi, xavi))
        teamJpaRepository.save(team)

        team.removeAll()

        assertEquals(2, memberJpaRepository.findAll().size)
    }
}
