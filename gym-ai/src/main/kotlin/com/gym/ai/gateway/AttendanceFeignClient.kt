package com.gym.com.gym.ai.gateway

import com.gym.com.gym.ai.controller.dto.AttendanceTodayStatsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(name = "gym-attendance", url = "\${gym.attendance.url}")
interface AttendanceFeignClient {

    @GetMapping("/v1/attendance/stats/today")
    fun getTodayStats(): AttendanceTodayStatsDto
}