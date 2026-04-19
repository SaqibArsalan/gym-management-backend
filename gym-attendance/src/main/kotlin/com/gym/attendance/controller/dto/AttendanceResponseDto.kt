package com.gym.attendance.controller.dto
import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.constant.MarkedBy
import com.gym.attendance.model.GymAttendance
import java.time.LocalDateTime
data class AttendanceResponseDto(
    val id: String?,
    val userId: String,
    val attendeeType: AttendeeType,
    val checkInTime: LocalDateTime,
    val checkOutTime: LocalDateTime?,
    val markedBy: MarkedBy,
    val notes: String?
) {
    companion object {
        fun createFrom(gymAttendance: GymAttendance): AttendanceResponseDto {
            return AttendanceResponseDto(
                id = gymAttendance.id,
                userId = gymAttendance.userId,
                attendeeType = gymAttendance.attendeeType,
                checkInTime = gymAttendance.checkInTime,
                checkOutTime = gymAttendance.checkOutTime,
                markedBy = gymAttendance.markedBy,
                notes = gymAttendance.notes
            )
        }
    }
}
