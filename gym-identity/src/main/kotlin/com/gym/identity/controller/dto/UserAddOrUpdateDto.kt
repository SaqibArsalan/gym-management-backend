package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.identity.model.User
import org.springframework.security.crypto.password.PasswordEncoder

//import org.springframework.security.crypto.password.PasswordEncoder

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserAddOrUpdateDto(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val accountStatus: String,
    val email: String,
    val password: String

) {
    fun toUserModel(passwordEncoder: PasswordEncoder) : User {
        return User(
            email = email,
            password = passwordEncoder.encode(password),
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            accountStatus = accountStatus
        )
    }

}