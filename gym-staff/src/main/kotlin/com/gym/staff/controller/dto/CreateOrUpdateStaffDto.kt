package com.gym.com.gym.staff.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateOrUpdateStaffDto(
    val userId: String,
    val name: String,
    val salary: Double,
    val hireDate: LocalDate
) {
}