package com.gym.com.gym.classes.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.com.gym.classes.constant.AttendanceStatus
import com.gym.com.gym.classes.constant.EnrollmentStatus
import com.gym.com.gym.classes.model.GymClass
import java.time.LocalDate
import java.time.LocalDateTime


data class EnrollmentRequestDto(
    val membershipId: String,
    val sessionId: String
)