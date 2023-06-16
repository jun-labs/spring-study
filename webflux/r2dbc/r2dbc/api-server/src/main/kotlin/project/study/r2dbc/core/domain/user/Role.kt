package project.study.r2dbc.core.domain.user

enum class Role {
    NORMAL,
    ADMIN;

    companion object {
        fun getRole(role: String) = when (role) {
            "NORMAL" -> NORMAL
            "ADMIN" -> ADMIN
            else -> throw IllegalArgumentException("올바른 역할을 입력해주세요.")
        }
    }
}
