package dev.bug.homebank.service

import dev.bug.homebank.entities.User
import java.util.Optional

interface UserService {

    fun save(user: User): User

    fun findById(id: Long): Optional<User>

    fun findByLogin(login: String): Optional<User>

    fun findAll(): List<User>

    fun findByRoleName(name: String): List<User>
}