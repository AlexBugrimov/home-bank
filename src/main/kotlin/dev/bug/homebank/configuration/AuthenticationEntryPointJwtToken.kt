package dev.bug.homebank.configuration

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationEntryPointJwtToken: AuthenticationEntryPoint {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        log.error("Unauthorized error: ${authException?.message}");
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}