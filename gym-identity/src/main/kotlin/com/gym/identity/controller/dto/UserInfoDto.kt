package com.gym.identity.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.identity.model.User

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserInfoDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val accountStatus: String,

) {
    companion object {
        fun createFrom(user: User): UserInfoDto {
            return UserInfoDto(
                id = user.id!!,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                phoneNumber = user.phoneNumber,
                accountStatus = user.accountStatus
            )
        }
    }
}