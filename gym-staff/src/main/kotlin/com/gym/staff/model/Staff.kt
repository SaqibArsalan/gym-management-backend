package com.gym.staff.model

import com.gym.com.gym.staff.controller.dto.CreateOrUpdateStaffDto
import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "staff")
data class Staff(
    @Column(name = "name")
    val name: String = "",

    @Column(name = "user_id")
    val userId: String = "",

    @Column(name = "salary")
    val salary: Double = 0.0,

    @Column(name = "hire_date")
    val hireDate: LocalDate = LocalDate.now(),

    ) : BaseEntity() {

    companion object {
        fun createFrom(staffDto: CreateOrUpdateStaffDto): Staff {
            return Staff(
                userId = staffDto.userId,
                name = staffDto.name,
                salary = staffDto.salary,
                hireDate = staffDto.hireDate,
            )
        }
    }
}



