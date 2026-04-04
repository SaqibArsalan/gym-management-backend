package com.gym.membership.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.gym.membership.model.MembershipSubscription
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class ActiveMembershipDto(
    val membershipId: String,
    val userId: String,
    val membershipPlanId: String,
    val memberName: String,
    val joinDate: LocalDate,
    val expiryDate: LocalDate,
    val membershipPlanName: String,
    val price: Double
) {
    companion object {
        fun createFrom(membershipSubscription: MembershipSubscription): ActiveMembershipDto {
            return ActiveMembershipDto(
                membershipId = membershipSubscription.id!!,
                userId = membershipSubscription.userId,
                membershipPlanId = membershipSubscription.membershipPlan.id!!,
                memberName = membershipSubscription.memberName,
                joinDate = membershipSubscription.joinDate,
                expiryDate = membershipSubscription.expiryDate,
                membershipPlanName = membershipSubscription.membershipPlan.name,
                price = membershipSubscription.membershipPlan.price
            )
        }
    }
}