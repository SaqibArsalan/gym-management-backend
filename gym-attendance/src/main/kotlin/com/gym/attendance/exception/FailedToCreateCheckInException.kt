package com.gym.attendance.exception

class FailedToCreateCheckInException(): Exception(
    String.format("Failed to create Check In" )
) {
}