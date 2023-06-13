package project.study.validation.test.type_unittest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.validation.model.LastVisitDate
import project.study.validation.model.VisitDate
import java.time.Instant
import java.time.LocalDateTime

@DisplayName("[UnitTest] 날짜 단위 테스트")
class DateUnitTest {

    @Test
    @DisplayName("toString을 재정의 했다면 원하는 양식대로 출력된다.")
    fun to_string_override_test_localdatetime() {
        val visitDate = VisitDate(LocalDateTime.now())

        assertEquals(visitDate.toString(), visitDate.visitDate.toString())
    }

    @Test
    @DisplayName("toString을 재정의 했다면 원하는 양식대로 출력된다.")
    fun to_string_override_test_instant() {
        val visitDate = LastVisitDate(Instant.now())

        assertEquals(visitDate.toString(), visitDate.lastVisitDate.toString())
    }
}
