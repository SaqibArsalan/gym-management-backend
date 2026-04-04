package com.gym.identity.service

import com.gym.identity.controller.dto.AuthenticationResponseDto
import com.gym.identity.controller.dto.CredentialDto
import com.gym.identity.exception.UserAuthenticationException
import com.gym.identity.repository.UserRepository
import com.gym.identity.util.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val jwt: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager,
    private val usersDetailsService: UsersDetailsService
    ) {

    @Transactional
    fun authenticate(credentialDto: CredentialDto): AuthenticationResponseDto {
        val user = userRepository.findByEmail(credentialDto.email).orElseThrow {
            throw BadCredentialsException("User not found")
        }

        val roles = usersDetailsService.getRolesFor(user.roles)

        try {
            val authentication : Authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.email, credentialDto.password))
            if (authentication.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = authentication
                val scopes = authentication.authorities.map { it.authority }
                val token: String = jwt.generateToken(user.email, user.id!!, scopes = scopes)
                return AuthenticationResponseDto(
                    token,
                    "",
                    userInfoDto = user.toUserInfo(),
                    "",
                    "",
                    scopes,
                    roles
                )
            } else {
                throw BadCredentialsException("Invalid token")
            }
        } catch (e : Exception) {
            throw UserAuthenticationException()
        }
    }
}