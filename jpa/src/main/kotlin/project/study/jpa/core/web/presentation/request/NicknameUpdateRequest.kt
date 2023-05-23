package project.study.jpa.core.web.presentation.request

class NicknameUpdateRequest(
    private val nickname: String,
    private val weight: Double
) {

    private constructor() : this("", 1.0) {

    }

    fun getNickname(): String {
        return nickname
    }

    fun getWeight(): Double {
        return weight
    }
}
