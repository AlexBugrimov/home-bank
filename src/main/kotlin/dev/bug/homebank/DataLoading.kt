package dev.bug.homebank

import dev.bug.homebank.entities.Role
import dev.bug.homebank.service.RoleService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoading(
    private val roleService: RoleService
): CommandLineRunner {

    override fun run(vararg args: String?) {
        roleService.saveAll(
            Role("ADMIN", "Bank administrator"),
            Role("USER", "User system"),
            Role( "MANAGER", "Manager")
        )
    }
}