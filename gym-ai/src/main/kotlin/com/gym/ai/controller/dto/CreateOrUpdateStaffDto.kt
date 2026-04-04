package com.gym.ai.controller.dto


data class Message(
    val role: String,
    val content: String
)

data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

data class ChatRequestDto(
    val message: String
)