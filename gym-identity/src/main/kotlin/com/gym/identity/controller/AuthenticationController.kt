package com.gym.identity.controller

import com.gym.identity.controller.dto.AuthenticationResponseDto
import com.gym.identity.controller.dto.CredentialDto
import com.gym.identity.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/auth")
class AuthenticationController(val authService: AuthenticationService) {

    @PostMapping("/login")
    fun login(@RequestBody credentialDto: CredentialDto) : ResponseEntity<Any> {
        val responseDto: AuthenticationResponseDto = authService.authenticate(credentialDto)
        return ResponseEntity(responseDto, HttpStatus.OK)

    }

}