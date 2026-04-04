package com.gym.identity.config.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class PreAuthorizationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val isValidated = request.getHeader("is-validated") ?: "false"
            if (isValidated.toBoolean()) {
                val userName = request.getHeader("user-name")
                val scopes = request.getHeader("user-scopes")?.split(",")?.toTypedArray() ?: arrayOf()
                injectSecurityPrincipal(userName, scopes)
            }
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request")
        }
    }

    private fun injectSecurityPrincipal(userName: String?, scopes: Array<String>) {
        if (SecurityContextHolder.getContext().authentication == null && userName != null) {
            val authorities = scopes.map { SimpleGrantedAuthority(it) }
            val userDetails = User(userName, "", authorities)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
    }
}
