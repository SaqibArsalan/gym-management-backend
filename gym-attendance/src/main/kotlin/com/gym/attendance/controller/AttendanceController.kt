package com.gym.attendance.controller

import com.gym.attendance.controller.dto.CheckInRequestDto
import com.gym.attendance.controller.dto.AttendanceResponseDto
import com.gym.attendance.controller.dto.AttendanceTodayStatsDto
import com.gym.attendance.service.AttendanceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/attendance")
class AttendanceController(private val attendanceService: AttendanceService) {

    @PostMapping("/check-in")
    fun checkIn(@RequestBody request: CheckInRequestDto): ResponseEntity<AttendanceResponseDto> =
        ResponseEntity(attendanceService.checkIn(request), HttpStatus.CREATED)

    @GetMapping()
    fun getAllAttendances(@RequestParam date: String): ResponseEntity<List<AttendanceResponseDto>> =
        ResponseEntity(attendanceService.getAllAttendances(date), HttpStatus.OK)

    @GetMapping("/stats/today")
    fun getTodayStats(): ResponseEntity<AttendanceTodayStatsDto> =
        ResponseEntity(attendanceService.getTodayStats(), HttpStatus.OK)

    @PatchMapping("/{visitId}/check-out")
    fun checkOut(@PathVariable visitId: String): ResponseEntity<AttendanceResponseDto> =
        ResponseEntity(attendanceService.checkOut(visitId), HttpStatus.OK)

}