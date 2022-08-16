package dev.bug.homebank.service

import dev.bug.homebank.entities.Role
import java.util.Optional

interface RoleService {

    fun save(role: Role): Role

    fun saveAll(vararg role: Role)

    fun findById(id: Long): Optional<Role>

    fun findAll(): List<Role>
}