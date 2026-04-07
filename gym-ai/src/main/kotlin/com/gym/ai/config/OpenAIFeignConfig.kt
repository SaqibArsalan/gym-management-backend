package com.gym.com.gym.ai.config

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAIFeignConfig(
    @Value("\${openai.api.key}") private val apiKey: String
) {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->
            template.header("Authorization", "Bearer $apiKey")
        }
    }

}
