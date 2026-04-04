package com.gym.membership.repository

import com.gym.membership.model.MembershipPlans
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MembershipPlanRepository: JpaRepository<MembershipPlans, String> {
}