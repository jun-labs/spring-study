package project.swagger.singlemodule.core.domain.repository

import org.springframework.stereotype.Repository
import project.swagger.singlemodule.core.domain.entity.User

@Repository
class UserRepository {
    private val factory = mutableMapOf<Long, User>()

    init {
        factory[1L] = User(1L, "Jackson")
        factory[2L] = User(2L, "Lina")
        factory[3L] = User(3L, "Lion")
        factory[4L] = User(4L, "Jekyll")
        factory[5L] = User(5L, "Paul")
    }

    fun save(user: User): User {
        factory[user.userId] = user
        return user
    }

    fun findUserById(id: Long): User? {
        return factory[id]?.let { it }
    }

    fun findUserByName(name: String): User? {
        return factory.values.firstOrNull { it.name == name }
    }

    fun findAllUser(): List<User> {
        return factory.values.stream()
            .toList()
    }
}
