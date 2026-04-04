package com.gym.membership.service

import com.gym.membership.controller.dto.ActiveMembershipDto
import com.gym.membership.controller.dto.MemberDto
import com.gym.membership.exception.*
import com.gym.membership.model.MembershipSubscription
import com.gym.membership.repository.MembershipSubscriptionRepository
import com.gym.membership.repository.MembershipPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MembershipSubscriptionService(
    private val membershipSubscriptionRepository: MembershipSubscriptionRepository,
    private val membershipPlanRepository: MembershipPlanRepository
    ) {

    @Transactional
    fun addMembershipSubscription(memberDto: MemberDto): MemberDto {

        try {
            val membershipPlan = membershipPlanRepository.findById(memberDto.membershipPlanId).orElseThrow {
                FailedToFetchMembershipPlanForIdException(memberDto.membershipPlanId)
            }
            val membershipSubscriptionModel = MembershipSubscription.createFrom(memberDto, membershipPlan)
            membershipSubscriptionRepository.save(membershipSubscriptionModel)
            return memberDto

        } catch (ex: FailedToFetchMembershipPlanForIdException) {
            throw ex
        } catch (e: Exception) {
            throw FailedToCreateMemberException()
        }
    }

    fun getActiveMemberships(userId: String): List<ActiveMembershipDto> {
        try {
            val activeMemberships = membershipSubscriptionRepository.findActiveMembershipsByUserId(userId)

            return activeMemberships.map {
                    membership -> ActiveMembershipDto(
                membership.id!!,
                membership.userId,
                membership.membershipPlan.id!!,
                membership.memberName,
                membership.joinDate,
                membership.expiryDate,
                membership.membershipPlan.name,
                membership.membershipPlan.price)
            }
        } catch (ex: Exception) {
            throw FailedToFetchActiveMembershipsExceptionForUser(userId)
        }
    }

    fun getMembershipsSubscriptions(): List<ActiveMembershipDto> {

        try {
            val membershipList = membershipSubscriptionRepository.findDistinctActiveMemberships()

            return membershipList.map {
                    membership -> ActiveMembershipDto(
                membership.id!!,
                membership.userId,
                membership.membershipPlan.id!!,
                membership.memberName,
                membership.joinDate,
                membership.expiryDate,
                membership.membershipPlan.name,
                membership.membershipPlan.price
            )
            }
        } catch (ex: Exception) {
            throw FailedToFetchMembershipSubscriptionsException()
        }
    }

    fun getTotalActiveMembers(): Long {
        try {
            return membershipSubscriptionRepository.countActiveMembers()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getTotalActiveSubscriptions(): Long {
        try {
            return membershipSubscriptionRepository.countActiveSubscriptions()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getNewSignupsForCurrentMonth(): Long {
        try {
            return membershipSubscriptionRepository.countNewSignups()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getMembershipDetails(id: String): ActiveMembershipDto {
        try {
            val membershipDetails = membershipSubscriptionRepository.findById(id).orElseThrow()
            return ActiveMembershipDto.createFrom(membershipDetails)
        } catch (ex: Exception) {
            throw FailedToFetchMembershipDetailsForIdException(id)
        }
    }
}