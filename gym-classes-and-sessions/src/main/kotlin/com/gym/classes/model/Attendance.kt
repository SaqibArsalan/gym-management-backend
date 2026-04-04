package com.gym.com.gym.classes.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "attendance")
data class Attendance(
    @Column(name = "member_id")
    val memberId: String = "",

    @Column(name = "status")
    val status: String = "",

    @Column(name = "check_in_time")
    val checkInTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "check_out_time")
    val checkOutTime: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    val session: Session = Session()

) : BaseEntity() {
}