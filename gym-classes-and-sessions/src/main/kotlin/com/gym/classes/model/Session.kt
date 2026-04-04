package com.gym.com.gym.classes.model

import com.gym.com.gym.classes.controller.dto.SessionCreateOrUpdateDto
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "sessions")
data class Session(
    @Column(name = "session_date")
    val sessionDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "trainer_id")
    val trainerId: String = "",

    @Column(name = "capacity")
    val capacity: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    val gymClass: GymClass = GymClass()

) : BaseEntity() {
    companion object {
        fun createFrom(sessionCreateOrUpdateDto: SessionCreateOrUpdateDto, gymClass: GymClass): Session {
            return Session(
                sessionDate = sessionCreateOrUpdateDto.sessionDate,
                trainerId = sessionCreateOrUpdateDto.trainerId,
                capacity = sessionCreateOrUpdateDto.capacity,
                gymClass = gymClass
            )
        }
    }
}