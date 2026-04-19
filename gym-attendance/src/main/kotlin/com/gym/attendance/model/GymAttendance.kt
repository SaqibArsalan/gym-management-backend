package com.gym.attendance.model

import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.constant.MarkedBy
import com.gym.attendance.controller.dto.AttendanceResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "gym_attendance")
data class GymAttendance(
    @Column(name = "user_id", nullable = false)
    val userId: String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "attendee_type", nullable = false)
    val attendeeType: AttendeeType = AttendeeType.MEMBER,
    @Column(name = "check_in_time", nullable = false)
    val checkInTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "check_out_time")
    var checkOutTime: LocalDateTime? = null,
    @Column(name = "marked_by")
    val markedBy: MarkedBy = MarkedBy.ADMIN,
    @Column(name = "notes")
    val notes: String? = null
) : BaseEntity()

fun GymAttendance.toResponseDto(): AttendanceResponseDto {
    return AttendanceResponseDto(
        id = this.id,
        userId = this.userId,
        attendeeType = this.attendeeType,
        checkInTime = this.checkInTime,
        checkOutTime = this.checkOutTime,
        markedBy = this.markedBy,
        notes = this.notes
    )
}
