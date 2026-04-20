package com.gym.attendance.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "user-service", url = "http://localhost:8080")
interface UserFeignClient {
    @GetMapping("/v1/identity/users/{id}")
    fun getUserById(@PathVariable id: String): UserResponse?
}

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val accountStatus: String,
)
