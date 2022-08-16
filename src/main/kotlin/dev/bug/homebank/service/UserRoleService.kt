package dev.bug.homebank.service

import dev.bug.homebank.entities.Role
import dev.bug.homebank.repository.RoleRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import java.util.List.copyOf

@Service
class UserRoleService(
    private val roleRepository: RoleRepository
    ): RoleService {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun save(role: Role): Role = roleRepository.save(role)

    override fun saveAll(vararg role: Role) {
        log.info("Saving roles...")
        roleRepository.saveAll(role.asList())
    }

    override fun findById(id: Long): Optional<Role> = roleRepository.findById(id)

    override fun findAll(): List<Role> = copyOf(roleRepository.findAll())
}