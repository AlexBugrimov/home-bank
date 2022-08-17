package dev.bug.homebank.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConstructorBinding
@ConfigurationProperties("jwt-security")
data class SecurityProperties(
    val secret: String?,
    val expiration: Duration?
)