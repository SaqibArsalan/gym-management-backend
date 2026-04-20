package com.gym.attendance.service


import com.gym.attendance.Repository.AttendanceRepository
import com.gym.attendance.constant.AttendeeType
import com.gym.attendance.controller.dto.CheckInRequestDto
import com.gym.attendance.controller.dto.AttendanceResponseDto
import com.gym.attendance.model.GymAttendance
import com.gym.attendance.client.UserFeignClient
import com.gym.attendance.client.StaffFeignClient
import com.gym.attendance.client.MembershipFeignClient
import com.gym.attendance.exception.FailedToCreateCheckInException
import com.gym.attendance.model.toResponseDto
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
                        throw ResponseStatusException(HttpStatus.NOT_FOUND, "No membership found for userId: ${request.userId}")
                    }

                    // Check if at least one membership is still active
                    val hasActiveMembership = memberships.any { it?.expiryDate?.isAfter(LocalDate.now()) == true || it?.expiryDate?.isEqual(LocalDate.now()) == true }

                    if (!hasActiveMembership) {
                        val latestExpiry = memberships.mapNotNull { it?.expiryDate }.maxOrNull()
                        throw ResponseStatusException(
                            HttpStatus.UNPROCESSABLE_ENTITY,
                            "All memberships expired. Latest expiry: $latestExpiry"
                        )
                    }
                }

                AttendeeType.STAFF -> {
                    // Validate user is actually a staff member via Feign client
                    val staff = staffFeignClient.getStaffByUserId(request.userId)
                        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No staff record found for userId: ${request.userId}")
                    // No membership check needed for staff
                }
            }

            // Duplicate active check-in guard
            val alreadyCheckedIn = attendanceRepository.existsByUserIdAndCheckOutTimeIsNull(request.userId)
            if (alreadyCheckedIn) {
                throw ResponseStatusException(HttpStatus.CONFLICT, "User already has an active check-in")
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
            throw FailedToCreateCheckInException()
        }
    }


}
