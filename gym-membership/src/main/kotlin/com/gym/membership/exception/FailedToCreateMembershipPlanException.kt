package com.gym.membership.exception

class FailedToCreateMembershipPlanException(): Exception(
    String.format("Failed to Create Membership Plan" )
) {
}