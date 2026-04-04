package com.gym.identity.controller

import com.gym.identity.controller.dto.UserAddOrUpdateDto
import com.gym.identity.controller.dto.UserInfoDto
import com.gym.identity.model.User
import com.gym.identity.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/identity/users")
class UsersController(val usersService: UsersService) {

    @GetMapping
    fun getUsers(): List<UserInfoDto> {
        return usersService.getUsers()
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String): UserInfoDto {
        return usersService.getUser(userId)
    }

    @PostMapping
    fun addUser(@RequestBody addUserAddOrUpdateDto: UserAddOrUpdateDto): ResponseEntity<*> {
        return ResponseEntity(usersService.addUser(addUserAddOrUpdateDto), HttpStatus.OK)
    }

    @GetMapping("/search")
    fun searchUsers(@RequestParam name: String): ResponseEntity<List<UserInfoDto>> {
        return ResponseEntity(usersService.searchUsers(name), HttpStatus.OK)
    }

}