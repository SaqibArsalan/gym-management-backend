package com.gym.membership.exception

class FailedToUpdateMemberException(): Exception(
    String.format("Failed to Update Member" )
) {
}