package project.study.database.test.common

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import project.study.database.common.database.DatabaseConnectionUtil

@DisplayName("[UnitTest] Connection 단위 테스트")
class ConnectionTest {

    @Test
    @DisplayName("DriverManager로 부터 Connection을 얻어올 수 있다.")
    fun get_connection_test() {
        val dbUtils = DatabaseConnectionUtil

        assertNotNull(dbUtils.getConnection())
    }
}
