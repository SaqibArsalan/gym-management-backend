package com.gym

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.cloud.openfeign.EnableFeignClients


@EnableFeignClients(basePackages = ["com.gym"])
@SpringBootApplication(scanBasePackages = ["com.gym"])
class GymManagementApplication {

    @Bean
    fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner {
        return CommandLineRunner {
            println("✅ Beans loaded by Spring Boot:")
            ctx.beanDefinitionNames.sorted().forEach { println(it) }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<GymManagementApplication>(*args)
}