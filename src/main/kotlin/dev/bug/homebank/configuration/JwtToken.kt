package dev.bug.homebank.configuration

import io.jsonwebtoken.*
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils.hasText
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtToken(val securityProperties: SecurityProperties) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private const val AUTH_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }

    fun generateJwtToken(authentication: Authentication): String {
        val securityUser = authentication.principal as SecurityUser
        return Jwts.builder()
            .setSubject(securityUser.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + securityProperties.expiration!!.toMillis()))
            .signWith(HS512, securityProperties.secret)
            .compact()
    }

    fun getLoginFromToken(token: String?): String? {
        return Jwts.parser()
            .setSigningKey(securityProperties.secret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun isValidateToken(authToken: String?): Boolean {
        try {
            Jwts.parser()
                .setSigningKey(securityProperties.secret)
                .parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            log.error("Invalid JWT signature: ${e.message}")
        } catch (e: MalformedJwtException) {
            log.error("Invalid JWT token: ${e.message}")
        } catch (e: ExpiredJwtException) {
            log.error("JWT token is expired: ${e.message}")
        } catch (e: UnsupportedJwtException) {
            log.error("JWT token is unsupported: ${e.message}")
        } catch (e: IllegalArgumentException) {
            log.error("JWT claims string is empty: ${e.message}")
        }
        return false
    }

    fun parse(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader(AUTH_HEADER)
        return if (hasText(headerAuth) && headerAuth.startsWith(TOKEN_PREFIX)) {
            headerAuth.substring(TOKEN_PREFIX.length, headerAuth.length)
        } else null
    }
}