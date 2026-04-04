package com.gym.membership.service

import com.gym.membership.controller.dto.DropdownResponseDto
import com.gym.membership.controller.dto.MembershipPlanDto
import com.gym.membership.exception.FailedToCreateMembershipPlanException
import com.gym.membership.exception.FailedToFetchMembershipPlanForIdException
import com.gym.membership.exception.FailedToFetchMembershipPlansException
import com.gym.membership.model.MembershipPlans
import com.gym.membership.repository.MembershipPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MembershipPlanService(
    private val membershipPlanRepository: MembershipPlanRepository
    ) {

    @Transactional
    fun createPlan(membershipPlanDto: MembershipPlanDto): MembershipPlanDto {

        try {
            val membershipModel = MembershipPlans.createFrom(membershipPlanDto)
            val savedPlan = membershipPlanRepository.save(membershipModel)
            return MembershipPlanDto.createFrom(savedPlan)

        } catch (e : Exception) {
            throw FailedToCreateMembershipPlanException()
        }
    }

    fun getAllPlans(): List<MembershipPlanDto> {
        try {
            val membershipPlans = membershipPlanRepository.findAll()
            return membershipPlans.map { membershipPlan -> MembershipPlanDto.createFrom(membershipPlan) }
        } catch (e: Exception) {
            throw FailedToFetchMembershipPlansException()
        }
    }

    fun getPlan(id: String): MembershipPlanDto {
        val membershipPlan = membershipPlanRepository.findById(id).orElseThrow {
            FailedToFetchMembershipPlanForIdException(id)
        }
        return MembershipPlanDto.createFrom(membershipPlan)
    }

    fun getAllPlansDropdown(): List<DropdownResponseDto> {
        try {
            val membershipPlans = membershipPlanRepository.findAll()
            return membershipPlans.map { membershipPlan -> DropdownResponseDto.createFrom(membershipPlan) }
        } catch (e: Exception) {
            throw FailedToFetchMembershipPlansException()
        }
    }

}