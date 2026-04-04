package com.gym.staff.service

import com.gym.com.gym.staff.controller.dto.CreateOrUpdateStaffDto
import com.gym.com.gym.staff.controller.dto.StaffResponseDto
import com.gym.com.gym.staff.exception.FailedToFetchStaffByNameException
import com.gym.staff.exception.FailedToCreateStaffException
import com.gym.staff.exception.FailedToFetchStaffForIdException
import com.gym.staff.exception.FailedToFetchStaffListException
import com.gym.staff.model.Staff
import com.gym.staff.repository.StaffRepository
import org.springframework.stereotype.Service

@Service
class StaffService(private val staffRepository: StaffRepository) {

    fun createStaff(addOrUpdateStaff: CreateOrUpdateStaffDto): StaffResponseDto {
        try {
            val staffModel = Staff.createFrom(addOrUpdateStaff)
            val savedStaff = staffRepository.save(staffModel)
            return StaffResponseDto.createFrom(savedStaff)
        } catch (ex: Exception) {
            throw FailedToCreateStaffException()
        }
    }

    fun getAllStaff(): List<StaffResponseDto> {
        try {
            val staffList = staffRepository.findAll()
            return staffList.map {
                staff -> StaffResponseDto(
                staff.id!!,

                    staff.userId,
                    staff.name,
                    staff.salary,
                    staff.hireDate
                )
            }
        } catch (ex: Exception) {
            throw FailedToFetchStaffListException()
        }
    }

    fun getStaff(userId: String): StaffResponseDto {
        try {
            val staff = staffRepository.findByUserId(userId)
            return StaffResponseDto(
                staff.id!!,
                staff.userId,
                staff.name,
                staff.salary,
                staff.hireDate
            )
        } catch (ex: Exception) {
            throw FailedToFetchStaffForIdException(userId)
        }
    }

    fun searchStaff(name: String): List<StaffResponseDto> {
        try {
            val staffList = staffRepository.findUsersByName(name)
            return staffList.map { StaffResponseDto.createFrom(it) }
        } catch (ex: FailedToFetchStaffByNameException) {
            throw FailedToFetchStaffByNameException(name)
        }
    }
}