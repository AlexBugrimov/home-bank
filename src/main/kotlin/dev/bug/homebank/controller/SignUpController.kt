package dev.bug.homebank.controller

import dev.bug.homebank.entities.User
import dev.bug.homebank.service.RoleService
import dev.bug.homebank.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    val passwordEncoder: PasswordEncoder,
    val userService: UserService,
    val roleService: RoleService
    ) {

    @PostMapping("/auth/sign-up")
    fun singUp(@RequestBody request: SignUpRequest): ResponseEntity<SignUpResponse> {
        val userExists = userService.findByLogin(request.login).isPresent
        if (userExists) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        val roleUser = roleService.findByName("USER");
        val createdUser = userService.save(
            User(
                null,
                request.firstName,
                request.lastName,
                request.email,
                request.login,
                passwordEncoder.encode(request.password),
                roleUser.get()
            )
        )
        return ResponseEntity.ok(createdUser.id?.let { SignUpResponse(it, createdUser.login) })
    }

    data class SignUpRequest(
        val firstName: String?,
        val lastName: String?,
        val email: String?,
        val login: String,
        val password: String)

    data class SignUpResponse(val id: Long, val login: String)
}

