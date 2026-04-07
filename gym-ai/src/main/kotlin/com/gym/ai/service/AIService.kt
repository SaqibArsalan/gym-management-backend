package com.gym.ai.service

import com.gym.ai.gateway.OpenAIServiceClient
import com.gym.ai.controller.dto.ChatRequest
import com.gym.ai.controller.dto.Message
import org.springframework.stereotype.Service

@Service
class AIService(private val openAIClient: OpenAIServiceClient) {

    fun chat(userMessage: String): Message? {
        val request = ChatRequest(
            model = "gpt-4o-mini",
            messages = listOf(
                Message("system", "You are a helpful gym assistant."),
                Message("user", userMessage)
            )
        )
        return try {
            openAIClient.chat(request).choices.firstOrNull()?.message
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}