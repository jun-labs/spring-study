package project.swagger.singlemodule.core.web.exception

import project.swagger.singlemodule.common.exception.DomainException

class UserNotFoundException : DomainException(
    "User Not Found",
    404
)
