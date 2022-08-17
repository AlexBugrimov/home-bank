package dev.bug.homebank.repository

import dev.bug.homebank.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {

    fun findByName(name: String): Optional<Role>
}