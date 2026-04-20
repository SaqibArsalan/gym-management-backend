package com.gym.staff.exception

class FailedToFetchStaffForIdException(userId: String): Exception(
    String.format("Failed to fetch Staff information for Id %s", userId )
) {
}