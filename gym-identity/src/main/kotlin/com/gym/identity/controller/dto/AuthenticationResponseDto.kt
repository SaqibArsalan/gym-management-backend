package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthenticationResponseDto(
    val token: String,
    val legacyToken: String,
    val userInfoDto: UserInfoDto,
    val expiresAt: String,
    val refreshToken: String,
    val scopes: List<String>,
    val roles: List<String>

) {}