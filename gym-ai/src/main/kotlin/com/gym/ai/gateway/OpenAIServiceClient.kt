package com.gym.ai.gateway

import com.gym.ai.controller.dto.ChatRequest
import com.gym.ai.controller.dto.OpenAIResponse
import com.gym.com.gym.ai.config.OpenAIFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "openai-service", url = "\${openai.base.url}", configuration = [OpenAIFeignConfig::class])
interface OpenAIServiceClient {

    @PostMapping("/chat/completions")
    fun chat(@RequestBody request: ChatRequest): OpenAIResponse
}