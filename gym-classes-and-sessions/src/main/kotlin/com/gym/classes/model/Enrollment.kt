package com.gym.com.gym.classes.model

import com.gym.com.gym.classes.constant.AttendanceStatus
import com.gym.com.gym.classes.constant.EnrollmentStatus
import com.gym.com.gym.classes.controller.dto.SessionCreateOrUpdateDto
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(
    name = "enrollment",
    uniqueConstraints = [UniqueConstraint(columnNames = ["session_id", "membership_id"])]
)
data class Enrollment(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    val session: Session = Session(),

    @Column(name = "membership_id", nullable = false)
    val membershipId: String = "",

    @Column(name = "user_id", nullable = false)
    val userId: String = "",

    @Column(name = "member_name", nullable = false)
    val memberName: String = "",

    @Column(name = "session_date", nullable = false)
    val sessionDate: LocalDate = LocalDate.now(),

    @Column(name = "class_name", nullable = false)
    val className: String = "",

    @Column(name = "enrolled_at", nullable = false)
    val enrolledAt: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: EnrollmentStatus = EnrollmentStatus.ENROLLED,

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_status", nullable = false)
    val attendanceStatus: AttendanceStatus = AttendanceStatus.PENDING

) : BaseEntity()
{
//    companion object {
//        fun createFrom(sessionCreateOrUpdateDto: SessionCreateOrUpdateDto, gymClass: GymClass): Enrollment {
//            return Enrollment(
//                sessionDate = sessionCreateOrUpdateDto.sessionDate,
//                trainerId = sessionCreateOrUpdateDto.trainerId,
//                capacity = sessionCreateOrUpdateDto.capacity,
//                gymClass = gymClass
//            )
//        }
//    }
}