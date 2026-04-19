package com.gym.attendance.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.constant.MarkedBy

@JsonIgnoreProperties(ignoreUnknown = true)
data class CheckInRequestDto(
    val userId: String,
    val attendeeType: AttendeeType,
    val markedBy: MarkedBy,
    val notes: String? = null
) {
    companion object {
//        fun createFrom(gymClass: GymClass): CheckInRequestDto {
//            return CheckInRequestDto(
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