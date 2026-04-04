package com.gym.identity.service

import com.gym.identity.model.Role
import com.gym.identity.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsersDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        println("🔍 CustomUserDetailsService: Loading user $username")
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        println("Loaded user: ${user.get().email}, Password: ${user.get().password}")

        val authorities = user.get().roles.map { SimpleGrantedAuthority(it.name) }

        return User(
            user.get().email, user.get().password, authorities
        )

    }

    fun getRolesFor(roles: Set<Role>) : List<String> {
        var rolesList: MutableList<String> = ArrayList()
        if (roles.isNotEmpty()) {
            rolesList = roles.map { it.name }.toMutableList()
        }
        return rolesList
    }
}