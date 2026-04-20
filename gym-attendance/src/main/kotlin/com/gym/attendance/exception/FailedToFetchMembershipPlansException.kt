package com.gym.membership.exception

class FailedToFetchMembershipPlansException(): Exception(
    String.format("Failed to fetch the list of Membership Plans" )
) {
}