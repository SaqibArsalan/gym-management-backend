package com.gym.ai.context

import com.gym.com.gym.ai.gateway.AttendanceFeignClient
import com.gym.com.gym.ai.gateway.MembershipFeignClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime

/**
 * Responsible for fetching live gym data from all internal services
 * and building the context section of the AI system prompt.
 *
 * Add new Feign clients here as you expand to more modules —
 * AIService stays untouched.
 */
@Component
class GymContextProvider(
    private val attendanceFeignClient: AttendanceFeignClient,
    private val membershipFeignClient: MembershipFeignClient
    // Add new Feign clients here, e.g.:
    // private val sessionsFeignClient: SessionsFeignClient,
    // private val membershipFeignClient: MembershipFeignClient,
) {
    private val logger = LoggerFactory.getLogger(GymContextProvider::class.java)

    fun buildContext(): String {
        val today = LocalDate.now()
        val timeNow = LocalTime.now()

        return buildString {
            appendLine("=== LIVE GYM DATA (as of $today $timeNow) ===")
            appendLine()
            appendAttendanceContext()
            appendMembershipContext()
            // Add more sections here as you add more Feign clients, e.g.:
            // appendSessionsContext()
            // appendMembershipContext()
        }
    }

    private fun StringBuilder.appendAttendanceContext() {
        val stats = runCatching { attendanceFeignClient.getTodayStats() }
            .onFailure { logger.error("[GymContextProvider] Failed to fetch attendance stats: ${it.message}", it) }
            .getOrNull()

        if (stats != null) {
            appendLine("Attendance:")
            appendLine("- Members checked in today: ${stats.presentToday}")
            appendLine("- Members currently inside: ${stats.currentlyInside}")
        } else {
            appendLine("Attendance: data unavailable.")
        }
        appendLine()
    }
    private fun StringBuilder.appendMembershipContext() {
        val stats = runCatching { membershipFeignClient.getNewSignupsForCurrentMonth() }
            .onFailure { logger.error("[GymContextProvider] Failed to fetch membership stats: ${it.message}", it) }
            .getOrElse { 0 }

        appendLine("Membership:")
        appendLine("- New sign-ups this month: $stats")
        appendLine()
    }
}
