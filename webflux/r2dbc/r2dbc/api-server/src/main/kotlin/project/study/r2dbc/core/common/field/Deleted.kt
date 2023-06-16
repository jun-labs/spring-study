package project.study.r2dbc.core.common.field

enum class Deleted {
    TRUE,
    FALSE;

    companion object {
        fun getDeleted(role: String) = when (role) {
            "TRUE" -> TRUE
            "FALSE" -> FALSE
            else -> throw IllegalArgumentException("올바른 역할을 입력해주세요.")
        }
    }
}
