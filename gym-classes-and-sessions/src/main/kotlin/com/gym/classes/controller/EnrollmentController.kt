package com.gym.com.gym.classes.controller

import com.gym.com.gym.classes.controller.dto.EnrollmentRequestDto
import com.gym.com.gym.classes.controller.dto.EnrollmentResponseDto
import com.gym.com.gym.classes.controller.dto.SessionCreateOrUpdateDto
import com.gym.com.gym.classes.controller.dto.SessionResponseDto
import com.gym.com.gym.classes.service.EnrollmentService
import com.gym.com.gym.classes.service.SessionManagementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/enrollments")
class EnrollmentController(private val enrollmentService: EnrollmentService) {

    @PostMapping
    fun enroll(@RequestBody request: EnrollmentRequestDto): ResponseEntity<EnrollmentResponseDto> =
        ResponseEntity(enrollmentService.enrollMember(request), HttpStatus.CREATED)

    @GetMapping("/sessions/{sessionId}")
    fun getBySession(@PathVariable sessionId: String): ResponseEntity<List<EnrollmentResponseDto>> =
        ResponseEntity(enrollmentService.getEnrollmentsForSession(sessionId), HttpStatus.OK)

    @GetMapping("/users/{userId}")
    fun getByUser(@PathVariable userId: String): ResponseEntity<List<EnrollmentResponseDto>> =
        ResponseEntity(enrollmentService.getEnrollmentsForUser(userId), HttpStatus.OK)
}