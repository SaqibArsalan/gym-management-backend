package com.gym.com.gym.ai.gateway

import com.gym.com.gym.ai.controller.dto.AttendanceTodayStatsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(name = "gym-membership", url = "\${gym.membership.url}")
interface MembershipFeignClient {

    @GetMapping("/v1/memberships/new-signups")
    fun getNewSignupsForCurrentMonth(): Long
}