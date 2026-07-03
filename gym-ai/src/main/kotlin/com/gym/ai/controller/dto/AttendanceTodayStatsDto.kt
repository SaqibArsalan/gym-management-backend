package com.gym.com.gym.ai.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttendanceTodayStatsDto(
    val presentToday: Int,
    val currentlyInside: Int
)
