package com.gym.ai.service

import com.gym.ai.context.GymContextProvider
import com.gym.ai.gateway.OpenAIServiceClient
import com.gym.ai.controller.dto.ChatRequest
import com.gym.ai.controller.dto.Message
import org.springframework.stereotype.Service

@Service
class AIService(
    private val openAiClient: OpenAIServiceClient,
    private val gymContextProvider: GymContextProvider
) {

    fun chat(userMessage: String): Message {
        val systemPrompt = buildString {
            appendLine("You are a helpful gym assistant. Answer questions about the gym using the real-time data below.")
            appendLine()
            append(gymContextProvider.buildContext())
            appendLine("Answer only based on the data above. If you don't have enough data, say so honestly.")
        }

        val messages = listOf(
            Message(role = "system", content = systemPrompt),
            Message(role = "user", content = userMessage)
        )

        return openAiClient.chat(ChatRequest(messages = messages, model = "gpt-4")).choices.first().message
    }
}