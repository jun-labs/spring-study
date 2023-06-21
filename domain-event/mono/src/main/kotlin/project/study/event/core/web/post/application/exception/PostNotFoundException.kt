package project.study.event.core.web.post.application.exception

import org.springframework.http.HttpStatus
import project.study.event.common.exception.BaseTypeException

class PostNotFoundException : BaseTypeException {
    override fun getCode(): Int {
        return 404
    }

    override fun getMessage(): String {
        return "게시글을 찾을 수 없습니다."
    }

    override fun getStatus(): HttpStatus {
        return HttpStatus.NOT_FOUND
    }
}
