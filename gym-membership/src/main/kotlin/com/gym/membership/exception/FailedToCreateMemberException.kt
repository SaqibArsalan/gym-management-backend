package com.gym.membership.exception

class FailedToCreateMemberException(): Exception(
    String.format("Failed to Create Member" )
) {
}