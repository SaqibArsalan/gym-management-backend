package com.gym.attendance.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.constant.MarkedBy

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttendanceTodayStatsDto(
    val presentToday: Int,
    val currentlyInside: Int
)
