package com.gym.membership.model

import com.gym.membership.controller.dto.MembershipPlanDto
import jakarta.persistence.*


@Entity
@Table(name = "membership_plans")
data class MembershipPlans(
    @Column(name = "name")
    val name: String = "",

    @Column(name = "duration_in_months")
    val durationInMonths: Int = 0,

    @Column(name = "price")
    val price: Double = 0.0,

    @Column(name = "description")
    val description: String = ""

) : BaseEntity() {

    companion object {
        fun createFrom(membershipPlanDto: MembershipPlanDto): MembershipPlans {
            return MembershipPlans(
                name = membershipPlanDto.name,
                durationInMonths = membershipPlanDto.durationInMonths,
                price = membershipPlanDto.price,
                description = membershipPlanDto.description
            )
        }
    }
}



