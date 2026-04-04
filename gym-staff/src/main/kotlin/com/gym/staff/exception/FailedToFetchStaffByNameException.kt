package com.gym.com.gym.staff.exception

class FailedToFetchStaffByNameException(name: String): Exception(
    String.format("Failed to fetch Staff information with name %s", name )
) {
}