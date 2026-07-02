package com.gym.attendance.Repository
import com.gym.attendance.model.GymAttendance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface AttendanceRepository : JpaRepository<GymAttendance, String> {
    fun existsByUserIdAndCheckInTimeBetweenAndCheckOutTimeIsNull(
        userId: String,
        start: LocalDateTime,
        end: LocalDateTime
    ): Boolean    fun findAllByUserId(userId: String): List<GymAttendance>
}
