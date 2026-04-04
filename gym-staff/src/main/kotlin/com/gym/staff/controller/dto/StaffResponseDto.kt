package com.gym.com.gym.staff.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.staff.model.Staff
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class StaffResponseDto(
    val id: String,
    val userId: String,
    val name: String,
    val salary: Double,
    val hireDate: LocalDate
) {
    companion object {
        fun createFrom(staff: Staff): StaffResponseDto {
            return StaffResponseDto(
                id = staff.id!!,
                userId = staff.userId,
                name = staff.name,
                salary = staff.salary,
                hireDate = staff.hireDate
            )
        }
    }
}