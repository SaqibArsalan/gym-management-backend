package com.gym.membership.exception

class FailedToFetchMembershipPlanForIdException(id: String): Exception(
    String.format("Failed to fetch details for Plan Id %s", id )
) {
}