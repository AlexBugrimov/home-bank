package dev.bug.homebank.service

import dev.bug.homebank.configuration.SecurityUser
import dev.bug.homebank.entities.User
import dev.bug.homebank.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserSecurityService(val userRepository: UserRepository): UserService, UserDetailsService {

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    override fun findByLogin(login: String): Optional<User> {
        return userRepository.findUserByLogin(login)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findByRoleName(name: String): List<User> {
        return userRepository.findAllByRoleName(name)
    }

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findUserByLogin(login)
            .orElseThrow { UsernameNotFoundException("User $login not found") }
        return SecurityUser(user)
    }
}