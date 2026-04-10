package com.gym.com.gym.classes.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class MembershipDto(
    val membershipId: String,
    val userId: String,
    val memberName: String,
    val expiryDate: LocalDate
)

@Component
class MembershipClient(
    private val restTemplate: RestTemplate,
    @Value("\${membership.service.base-url:http://localhost:8080}") private val baseUrl: String
) {

    fun getMembershipById(membershipId: String): MembershipDto {
        return try {
            restTemplate.getForObject(
                "$baseUrl/v1/memberships/$membershipId",
                MembershipDto::class.java
            ) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Membership not found: $membershipId")
        } catch (ex: HttpClientErrorException.NotFound) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Membership not found: $membershipId")
        }
    }
}
