package com.gym.attendance.service


import com.gym.attendance.Repository.AttendanceRepository
import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.controller.dto.CheckInRequestDto
import com.gym.attendance.controller.dto.AttendanceResponseDto
import com.gym.attendance.model.GymAttendance
import com.gym.attendance.client.UserFeignClient
import com.gym.attendance.client.StaffFeignClient
import com.gym.attendance.client.MembershipFeignClient
import com.gym.attendance.controller.dto.AttendanceTodayStatsDto
import com.gym.attendance.exception.AlreadyCheckedOutException
import com.gym.attendance.exception.FailedToCreateCheckInException
import com.gym.attendance.exception.FailedToFetchMembershipForUserException
import com.gym.attendance.exception.FailedToFetchVisitByIdException
import com.gym.attendance.model.toResponseDto
import com.gym.com.gym.attendance.exception.FailedToFetchStaffException

import com.gym.com.gym.attendance.exception.NoActiveMembershipException
import com.gym.com.gym.attendance.exception.UserAlreadyCheckedInException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class AttendanceService(
    private val userFeignClient: UserFeignClient,
    private val membershipFeignClient: MembershipFeignClient,
    private val staffFeignClient: StaffFeignClient,
    private val attendanceRepository: AttendanceRepository
) {

    @Transactional
    fun checkIn(request: CheckInRequestDto): AttendanceResponseDto {

        try {
            // Validate user exists via Feign client
            val user = userFeignClient.getUserById(request.userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: ${request.userId}")

            when (request.attendeeType) {
                AttendeeType.MEMBER -> {
                    // membershipId is required for members
                    val memberships = membershipFeignClient.getMembershipByUserId(user.id)

                    if (memberships.isEmpty()) {
                        throw FailedToFetchMembershipForUserException(user.firstName)
                    }

                    // Check if at least one membership is still active
                    val hasActiveMembership = memberships.any { it?.expiryDate?.isAfter(LocalDate.now()) == true || it?.expiryDate?.isEqual(LocalDate.now()) == true }

                    if (!hasActiveMembership) {
                        val latestExpiry = memberships.mapNotNull { it?.expiryDate }.maxOrNull()
                        throw NoActiveMembershipException(latestExpiry.toString())
                    }
                }

                AttendeeType.STAFF -> {
                    // Validate user is actually a staff member via Feign client
                    staffFeignClient.getStaffByUserId(request.userId)
                        ?: throw FailedToFetchStaffException()
                }
            }

            // Duplicate active check-in guard
            val alreadyCheckedIn = attendanceRepository.existsByUserIdAndCheckOutTimeIsNull(request.userId)
            if (alreadyCheckedIn) {
                throw UserAlreadyCheckedInException()
            }

            val attendance = attendanceRepository.save(
                GymAttendance(
                    userId = request.userId,
                    attendeeType = request.attendeeType,
                    checkInTime = LocalDateTime.now(),
                    markedBy = request.markedBy,
                    notes = request.notes
                )
            )

            return attendance.toResponseDto()

        } catch (ex: FailedToCreateCheckInException) {
            throw ex
        } catch (ex: UserAlreadyCheckedInException) {
            throw ex
        } catch (ex: NoActiveMembershipException) {
            throw ex
        } catch (ex: FailedToFetchMembershipForUserException) {
            throw ex
        } catch (ex: FailedToFetchStaffException) {
            throw ex
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred during check-in", ex)
        }
    }

    fun getAllAttendances(date: String): List<AttendanceResponseDto> {
        val attendances = attendanceRepository.findAll().filter { it.checkInTime.toLocalDate().toString() == date }
        return attendances.map { it.toResponseDto() }
    }

    fun getTodayStats(): AttendanceTodayStatsDto {
        val today = LocalDate.now()
        val presentToday = attendanceRepository.findAll().count { it.checkInTime.toLocalDate() == today }
        val currentlyInside = attendanceRepository.findAll().count { it.checkInTime.toLocalDate() == today && it.checkOutTime == null }
        return AttendanceTodayStatsDto(presentToday = presentToday, currentlyInside = currentlyInside)
    }

    @Transactional
    fun checkOut(visitId: String): AttendanceResponseDto {

        try {
            val attendance = attendanceRepository.findById(visitId).orElseThrow {
                FailedToFetchVisitByIdException(visitId)
            }

            if (attendance.checkOutTime != null) {
                throw AlreadyCheckedOutException()
            }

            attendance.checkOutTime = LocalDateTime.now()
            return attendanceRepository.save(attendance).toResponseDto()

        } catch (ex: AlreadyCheckedOutException) {
            throw ex
        } catch (ex: FailedToFetchVisitByIdException) {
            throw ex
        }

    }


}
