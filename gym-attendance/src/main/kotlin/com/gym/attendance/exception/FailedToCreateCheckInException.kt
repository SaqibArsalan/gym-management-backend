package com.gym.attendance.exception

class FailedToCreateCheckInException(): Exception(
    String.format("User already has an active check-in" )
) {
}