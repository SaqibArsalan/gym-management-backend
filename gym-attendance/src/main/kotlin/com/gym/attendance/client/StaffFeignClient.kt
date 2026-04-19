package com.gym.attendance.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "staff-service", url = "http://localhost:8081")
interface StaffFeignClient {
    @GetMapping("/api/staff/{userId}")
    fun getStaffByUserId(@PathVariable userId: String): StaffResponse?

    @GetMapping("/api/trainers/{userId}")
    fun getTrainerByUserId(@PathVariable userId: String): TrainerResponse?
}

data class StaffResponse(
    val id: String,
    val userId: String
)

data class TrainerResponse(
    val id: String,
    val userId: String
)
