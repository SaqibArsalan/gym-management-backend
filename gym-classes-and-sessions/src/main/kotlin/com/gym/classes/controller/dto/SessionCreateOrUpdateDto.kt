package com.gym.com.gym.classes.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.com.gym.classes.model.GymClass
import java.time.LocalDate
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class SessionCreateOrUpdateDto(
    val classId: String,
    val sessionDate: LocalDateTime,
    val trainerId: String,
    val capacity: Int
) {
    companion object {
//        fun createFrom(gymClass: GymClass): SessionCreateOrUpdateDto {
//            return SessionCreateOrUpdateDto(
//                className = gymClass.name,
//                description = gymClass.description,
//                trainerId = gymClass.trainerId,
//                capacity = gymClass.capacity,
//                startTime = gymClass.startTime,
//                endTime = gymClass.endTime
//            )
//        }
    }
}