package dev.bug.homebank.configuration

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Objects.nonNull
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(
    val jwtToken: JwtToken,
    val userService: UserDetailsService
    ): OncePerRequestFilter() {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authToken: String? = jwtToken.parse(request)
            if (nonNull(authToken) && jwtToken.isValidateToken(authToken)) {
                val login: String? = jwtToken.getLoginFromToken(authToken)
                val userDetails: UserDetails = userService.loadUserByUsername(login)
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            log.error("Cannot set user authentication: $e")
            throw BadCredentialsException("The user doesn't authenticate")
        }
        filterChain.doFilter(request, response)
    }
}