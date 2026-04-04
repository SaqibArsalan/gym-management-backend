package com.gym.membership.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.membership.model.MembershipPlans

@JsonIgnoreProperties(ignoreUnknown = true)
data class MembershipPlanDto(
    val name: String,
    val durationInMonths: Int,
    val price: Double,
    val description: String
) {
    companion object {
        fun createFrom(membershipPlanModel: MembershipPlans): MembershipPlanDto {
            return MembershipPlanDto(
                membershipPlanModel.name,
                membershipPlanModel.durationInMonths,
                membershipPlanModel.price,
                membershipPlanModel.description
            )
        }
    }
}