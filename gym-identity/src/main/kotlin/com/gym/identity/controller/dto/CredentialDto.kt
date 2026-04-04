package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CredentialDto(
    val email: String,
    val password: String

) {}