package com.gym.classes.controller

import com.gym.classes.service.ClassManagementService
import com.gym.com.gym.classes.controller.dto.ClassDto
import com.gym.com.gym.classes.controller.dto.ClassResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/classes")
class ClassManagementController(val classManagementService: ClassManagementService) {

    @PostMapping
    fun createClass(@RequestBody classDto: ClassDto): ResponseEntity<ClassResponseDto> {
        return ResponseEntity(classManagementService.createClass(classDto), HttpStatus.CREATED)
    }

    @GetMapping
    fun getClasses(): ResponseEntity<List<ClassResponseDto>> {
        return ResponseEntity(classManagementService.getAllClasses(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getClassDetails(@PathVariable id: String): ResponseEntity<ClassDto> {
        return ResponseEntity(classManagementService.getClassDetails(id), HttpStatus.OK)
    }

    @GetMapping("/search")
    fun searchClass(@RequestParam name: String): ResponseEntity<List<ClassResponseDto>> {
        return ResponseEntity(classManagementService.searchClass(name), HttpStatus.OK)
    }

}