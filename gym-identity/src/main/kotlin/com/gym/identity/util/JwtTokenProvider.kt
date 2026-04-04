package com.gym.identity.util


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(ByteArray(32).also { SecureRandom().nextBytes(it) })
    private val expirationTime: Long = 86400000 // 24 hours

    // Generate JWT Token
    fun generateToken(email: String, userId: String, scopes: List<String>): String {
        val claims = mutableMapOf<String, Any>(
            "USER_ID_KEY" to userId,
            "SCOPES_KEY" to scopes
        )

        return Jwts.builder()
            .subject(email)
            .claims(claims)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 86400000)) // 1-day expiry
            .signWith(secretKey)
            .compact()
    }

    // Validate JWT Token
    fun validateToken(token: String): Boolean {
        return try {
            getClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    // Extract Username from Token
    fun getUsername(token: String): String {
        return getClaims(token).subject
    }

    // Get Authentication from Token
    fun getAuthentication(token: String): Authentication {
        val username = getUsername(token)
        val userDetails = org.springframework.security.core.userdetails.User(username, "", emptyList())
        return org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, token, emptyList())
    }

    // Parse Token Claims
    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
