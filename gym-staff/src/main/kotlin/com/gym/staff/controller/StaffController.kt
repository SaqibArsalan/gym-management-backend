package com.gym.staff.controller

import com.gym.com.gym.staff.controller.dto.CreateOrUpdateStaffDto
import com.gym.com.gym.staff.controller.dto.StaffResponseDto
import com.gym.staff.model.Staff
import com.gym.staff.service.StaffService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/staff")
class StaffController(val staffService: StaffService) {

    @PostMapping
    fun createStaff(@RequestBody createOrUpdateStaffDto: CreateOrUpdateStaffDto): ResponseEntity<StaffResponseDto> {
        return ResponseEntity(staffService.createStaff(createOrUpdateStaffDto), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllStaff(): ResponseEntity<List<StaffResponseDto>> {
        return ResponseEntity(staffService.getAllStaff(), HttpStatus.OK)
    }

    @GetMapping("/{userId}")
    fun getStaffById(@PathVariable("userId") userId: String): ResponseEntity<StaffResponseDto> {
        return ResponseEntity(staffService.getStaff(userId), HttpStatus.OK)
    }

    @GetMapping("/search")
    fun searchStaff(@RequestParam name: String): ResponseEntity<List<StaffResponseDto>> {
        return ResponseEntity(staffService.searchStaff(name), HttpStatus.OK)
    }
}