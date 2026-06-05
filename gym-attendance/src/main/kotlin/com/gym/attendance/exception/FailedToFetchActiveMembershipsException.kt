package com.gym.membership.exception

class FailedToFetchActiveMembershipsExceptionForUser(userId: String): Exception(
    String.format("Failed to fetch active memberships for user with Id %s", userId )
) {
}