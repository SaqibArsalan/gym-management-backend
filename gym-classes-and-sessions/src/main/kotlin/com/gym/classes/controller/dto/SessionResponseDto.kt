package com.gym.com.gym.classes.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.com.gym.classes.model.GymClass
import com.gym.com.gym.classes.model.Session
import java.time.LocalDate
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class SessionResponseDto(
    val id: String,
    val classId: String,
    val trainerId: String,
    val className: String,
    val sessionDate: LocalDateTime,
    val capacity: Int
) {
    companion object {
        fun createFrom(session: Session): SessionResponseDto {
            return SessionResponseDto(
                id = session.id!!,
                classId = session.gymClass.id!!,
                className = session.gymClass.name,
                trainerId = session.trainerId,
                capacity = session.capacity,
                sessionDate = session.sessionDate
            )
        }
    }
}