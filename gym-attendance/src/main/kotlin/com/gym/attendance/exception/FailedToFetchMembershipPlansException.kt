
package com.gym.attendance.exception


class FailedToFetchMembershipPlansException(): Exception(
    String.format("Failed to fetch the list of Membership Plans" )
) {
}