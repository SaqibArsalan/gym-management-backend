package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.identity.model.Role

@JsonIgnoreProperties(ignoreUnknown = true)
data class RoleAddOrUpdateDto(
    val name: String,
    val description: String,
    val status: String,
    val scopes: List<String>

) {
    fun toRoleModel() : Role {
        return Role(
            name = name,
            description = description,
            status = status,
            scopes = scopes
        )
    }

}