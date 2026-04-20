package com.gym.classes.controller.dto

import com.gym.com.gym.classes.constant.AttendanceStatus
import com.gym.com.gym.classes.constant.EnrollmentStatus
import com.gym.com.gym.classes.model.GymClass
import java.time.LocalDate
import java.time.LocalDateTime


data class EnrollmentResponseDto(
    val id: String?,
    val sessionId: String,
    val membershipId: String,
    val userId: String,
    val memberName: String,
    val sessionDate: LocalDate,
    val className: String,
    val enrolledAt: LocalDateTime,
    val status: EnrollmentStatus,
    val attendanceStatus: AttendanceStatus
)