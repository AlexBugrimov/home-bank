package dev.bug.homebank.service

import dev.bug.homebank.entities.Role
import dev.bug.homebank.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.List.copyOf

@Service
class UserRoleService(
    private val roleRepository: RoleRepository
    ): RoleService {

    override fun save(role: Role): Role = roleRepository.save(role)

    override fun saveAll(vararg role: Role) {
        roleRepository.saveAll(role.asList())
    }

    override fun findById(id: Long): Optional<Role> = roleRepository.findById(id)

    override fun findAll(): List<Role> = copyOf(roleRepository.findAll())
}