package dev.bug.homebank.repository

import dev.bug.homebank.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findUserByLogin(login: String): Optional<User>

    fun findAllByRoleName(name: String): List<User>
}