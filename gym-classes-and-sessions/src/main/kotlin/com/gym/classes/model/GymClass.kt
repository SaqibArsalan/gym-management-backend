package com.gym.com.gym.classes.model

import com.gym.com.gym.classes.controller.dto.ClassDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "classes")
data class GymClass(
    @Column(name = "name")
    val name: String = "",

    @Column(name = "description")
    val description: String = "",

    @Column(name = "trainer_id")
    val trainerId: String = "",

    @Column(name = "capacity")
    val capacity: Int = 0,

    @Column(name = "start_time")
    val startTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "end_time")
    val endTime: LocalDateTime = LocalDateTime.now(),


) : BaseEntity() {

    companion object {
        fun createFrom(classDto: ClassDto): GymClass {
            return GymClass(
                name = classDto.className,
                description = classDto.description,
                trainerId = classDto.trainerId,
                capacity = classDto.capacity,
                startTime = classDto.startTime,
                endTime = classDto.endTime
            )
        }
    }
}