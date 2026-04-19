package com.gym.com.gym.classes.service

import com.gym.classes.controller.dto.EnrollmentResponseDto
import com.gym.classes.model.Enrollment
import com.gym.com.gym.classes.Repository.SessionManagementRepository
import com.gym.com.gym.classes.client.MembershipClient
import com.gym.com.gym.classes.controller.dto.EnrollmentRequestDto
import com.gym.com.gym.classes.repository.EnrollmentRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class EnrollmentService(
    private val enrollmentRepository: EnrollmentRepository,
    private val sessionRepository: SessionManagementRepository,
    private val membershipClient: MembershipClient
) {

    @Transactional
    fun enrollMember(request: EnrollmentRequestDto): EnrollmentResponseDto {

        // --- Fetch session ---
        val session = sessionRepository.findById(request.sessionId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found: ${request.sessionId}")
        }

        // --- Fetch membership via HTTP call to membership service ---
        val membership = membershipClient.getMembershipById(request.membershipId)

        // --- Business Rule 1: Active membership check ---
        // session.sessionDate is LocalDateTime, membership.expiryDate is LocalDate
        if (membership.expiryDate.isBefore(session.sessionDate.toLocalDate())) {
            throw ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Membership expired on ${membership.expiryDate}. Cannot enroll in session on ${session.sessionDate.toLocalDate()}"
            )
        }

        // --- Business Rule 2: Capacity check ---
        val activeEnrollments = enrollmentRepository.countActiveEnrollmentsBySessionId(session.id!!)
        if (activeEnrollments >= session.capacity) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "Session is fully booked (capacity: ${session.capacity})"
            )
        }

        // --- Duplicate enrollment check (also enforced by DB unique constraint) ---
        if (enrollmentRepository.existsBySessionIdAndMembershipId(session.id!!, membership.membershipId)) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "Member is already enrolled in this session"
            )
        }

        // --- Denormalize and persist ---
        val enrollment = enrollmentRepository.save(
            Enrollment(
                session = session,
                membershipId = membership.membershipId,
                userId = membership.userId,
                memberName = membership.memberName,
                sessionDate = session.sessionDate.toLocalDate(),
                className = session.gymClass.name
            )
        )

        return enrollment.toResponseDto()
    }

    fun getEnrollmentsForSession(sessionId: String): List<EnrollmentResponseDto> =
        enrollmentRepository.findAllBySessionId(sessionId).map { it.toResponseDto() }

    fun getEnrollmentsForUser(userId: String): List<EnrollmentResponseDto> =
        enrollmentRepository.findAllByUserId(userId).map { it.toResponseDto() }

    private fun Enrollment.toResponseDto() = EnrollmentResponseDto(
        id = id,
        sessionId = session.id!!,
        membershipId = membershipId,
        userId = userId,
        memberName = memberName,
        sessionDate = sessionDate,
        className = className,
        enrolledAt = enrolledAt,
        status = status,
        attendanceStatus = attendanceStatus
    )
}