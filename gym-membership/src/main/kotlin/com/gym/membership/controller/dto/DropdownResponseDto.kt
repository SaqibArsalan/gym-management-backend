package com.gym.membership.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.membership.model.MembershipPlans

@JsonIgnoreProperties(ignoreUnknown = true)
data class DropdownResponseDto(
    val id: String,
    val name: String
) {
    companion object {
        fun createFrom(membershipPlanModel: MembershipPlans): DropdownResponseDto {
            return DropdownResponseDto(
                membershipPlanModel.id!!,
                membershipPlanModel.name
            )
        }
    }
}