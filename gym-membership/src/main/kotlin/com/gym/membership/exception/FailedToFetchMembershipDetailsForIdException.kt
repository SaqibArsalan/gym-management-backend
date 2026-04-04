package com.gym.membership.exception

class FailedToFetchMembershipDetailsForIdException(id: String): Exception(
    String.format("Failed to fetch details for Id %s", id )
) {
}