package com.gym.attendance.Repository
import com.gym.attendance.model.GymAttendance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface AttendanceRepository : JpaRepository<GymAttendance, String> {
    fun existsByUserIdAndCheckOutTimeIsNull(userId: String): Boolean
    fun findAllByUserId(userId: String): List<GymAttendance>
}
