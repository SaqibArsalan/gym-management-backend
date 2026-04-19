package com.gym.attendance.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDate
import java.time.LocalDateTime

@FeignClient(name = "membership-service", url = "http://localhost:8080")
interface MembershipFeignClient {
    @GetMapping("/v1/memberships/user/{userId}")
    fun getMembershipByUserId(@PathVariable userId: String): List<MembershipResponse?>
}

data class MembershipResponse(
    val membershipId: String,
    val userId: String,
    val membershipPlanId: String,
    val memberName: String,
    val joinDate: LocalDate,
    val expiryDate: LocalDate,
    val membershipPlanName: String,
    val price: Double
)
