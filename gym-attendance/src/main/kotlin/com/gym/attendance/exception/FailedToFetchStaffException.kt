package com.gym.com.gym.attendance.exception

class FailedToFetchStaffException(): Exception(
    String.format("No staff record found" )
) {
}