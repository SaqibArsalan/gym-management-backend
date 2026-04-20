package com.gym.membership.exception

class FailedToFetchMembershipSubscriptionsException(): Exception(
    String.format("Failed to fetch the list of Membership Subscriptions" )
) {
}