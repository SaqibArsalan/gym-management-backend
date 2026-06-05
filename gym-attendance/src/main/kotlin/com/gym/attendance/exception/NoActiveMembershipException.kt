package com.gym.com.gym.attendance.exception

class NoActiveMembershipException(latestExpiry: String?): Exception(
    String.format("All memberships expired. Latest expiry: $latestExpiry" )
) {
}