package com.gym.identity.controller

import com.gym.identity.controller.dto.RoleAddOrUpdateDto
import com.gym.identity.controller.dto.RoleToUserDto
import com.gym.identity.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/role")
class RoleController(val roleService: RoleService) {

    @PostMapping
    fun addRole(@RequestBody addOrUpdateDto: RoleAddOrUpdateDto): ResponseEntity<*> {
        return ResponseEntity(roleService.addRole(addOrUpdateDto), HttpStatus.CREATED)
    }

    @PostMapping("/assign-role-to-user")
    fun assignToUser(@RequestBody role: RoleToUserDto): ResponseEntity<*> {
        return ResponseEntity(roleService.assignRoleToUser(role), HttpStatus.CREATED)
    }

}