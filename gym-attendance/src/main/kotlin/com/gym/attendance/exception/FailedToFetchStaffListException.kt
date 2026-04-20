package com.gym.staff.exception

class FailedToFetchStaffListException(): Exception(
    String.format("Failed to fetch Staff list")
) {
}