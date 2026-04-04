package com.gym.com.gym.ai.controller

import com.gym.ai.controller.dto.ChatRequest
import com.gym.ai.controller.dto.ChatRequestDto
import com.gym.ai.service.AIService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/ai")
class AIController(val aiService: AIService) {

    @PostMapping("/chat")
    fun chat(@RequestBody chat: ChatRequestDto): ResponseEntity<String> {
        return ResponseEntity(aiService.chat(chat.message), HttpStatus.CREATED)
    }

}