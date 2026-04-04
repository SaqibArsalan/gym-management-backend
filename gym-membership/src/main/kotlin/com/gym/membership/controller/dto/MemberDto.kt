package com.gym.membership.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class MemberDto(
    val userId: String,
    val membershipPlanId: String,
    val memberName: String,
    val joinDate: LocalDate,
    val durationInMonths: Int
) {
}