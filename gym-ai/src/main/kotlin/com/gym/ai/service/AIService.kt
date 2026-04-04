package com.gym.ai.service

import com.gym.ai.controller.dto.ChatRequest
import com.gym.ai.controller.dto.Message
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class AIService(builder: WebClient.Builder) {

    private val webClient: WebClient = builder
        .baseUrl("https://api.openai.com/v1")
        .build()

    fun chat(userMessage: String): String? {

        val request = ChatRequest(
            model = "gpt-4o-mini",
            messages = listOf(
                Message("system", "You are a helpful gym assistant."),
                Message("user", userMessage)
            )
        )
        try {
            return webClient.post()
                .uri("/chat/completions")
                .header("Content-Type", "application/json") // ✅ IMPORTANT
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String::class.java)
                .block()

        } catch (e: Exception) {
            e.printStackTrace()
            return "Sorry, I couldn't process your request at the moment."
        }

    }
}