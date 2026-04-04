package com.gym.com.gym.classes.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.com.gym.classes.model.GymClass
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClassResponseDto(
    val id: String,
    val className: String,
    val description: String,
    val trainerId: String,
    val capacity: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) {
    companion object {
        fun createFrom(gymClass: GymClass): ClassResponseDto {
            return ClassResponseDto(
                id = gymClass.id!!,
                className = gymClass.name,
                description = gymClass.description,
                trainerId = gymClass.trainerId,
                capacity = gymClass.capacity,
                startTime = gymClass.startTime,
                endTime = gymClass.endTime
            )
        }
    }
}