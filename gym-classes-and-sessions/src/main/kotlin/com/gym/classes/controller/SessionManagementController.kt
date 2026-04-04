package com.gym.com.gym.classes.controller

import com.gym.com.gym.classes.controller.dto.SessionCreateOrUpdateDto
import com.gym.com.gym.classes.controller.dto.SessionResponseDto
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
@RequestMapping("/v1/sessions")
class SessionManagementController(val sessionManagementService: SessionManagementService) {

    @PostMapping("/classes/{classId}")
    fun createSessionForClass(@RequestBody sessionCreateOrUpdateDto: SessionCreateOrUpdateDto, @PathVariable classId: String): ResponseEntity<SessionResponseDto> {
        return ResponseEntity(sessionManagementService.createSessionForClass(sessionCreateOrUpdateDto, classId), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllSessions(): ResponseEntity<List<SessionResponseDto>> {
        return ResponseEntity(sessionManagementService.getAllSessions(), HttpStatus.OK)
    }

//    @GetMapping("/{id}")
//    fun getClassDetails(@PathVariable id: String): ResponseEntity<ClassDto> {
//        return ResponseEntity(sessionManagementService.getClassDetails(id), HttpStatus.OK)
//    }

}