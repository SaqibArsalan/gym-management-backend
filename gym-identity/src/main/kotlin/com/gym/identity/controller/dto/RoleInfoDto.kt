package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RoleInfoDto(
    val name: String,
    val status: String,
    val scopes: List<String>

) {
}