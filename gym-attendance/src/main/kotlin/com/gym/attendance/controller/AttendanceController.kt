package com.gym.attendance.controller

import com.gym.attendance.controller.dto.CheckInRequestDto
import com.gym.attendance.controller.dto.AttendanceResponseDto
import com.gym.attendance.service.AttendanceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/attendance")
class AttendanceController(private val attendanceService: AttendanceService) {

    @PostMapping("/check-in")
    fun checkIn(@RequestBody request: CheckInRequestDto): ResponseEntity<AttendanceResponseDto> =
        ResponseEntity(attendanceService.checkIn(request), HttpStatus.CREATED)

}