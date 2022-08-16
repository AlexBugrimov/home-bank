package dev.bug.homebank.repository

import dev.bug.homebank.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long>