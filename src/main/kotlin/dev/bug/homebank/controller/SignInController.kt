package dev.bug.homebank.controller

import dev.bug.homebank.configuration.JwtToken
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController(
    val authenticationManager: AuthenticationManager,
    val jwtToken: JwtToken,
) {

    @PostMapping("/auth/sign-in")
    fun singIn(@RequestBody request: SingInRequest): ResponseEntity<SingInResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.login, request.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        return ResponseEntity.ok(SingInResponse(jwtToken.generateJwtToken(authentication)))
    }

    data class SingInRequest(val login: String, val password: String)
    data class SingInResponse(val token: String)
}