package com.gym.attendance.exception

class FailedToFetchMembershipForUserException(name: String): Exception(
    String.format("Failed to fetch Membership information for user %s", name )
) {
}