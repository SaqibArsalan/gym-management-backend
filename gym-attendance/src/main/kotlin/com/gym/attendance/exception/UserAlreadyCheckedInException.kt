package com.gym.com.gym.attendance.exception

class UserAlreadyCheckedInException(): Exception(
    String.format("User is already checked-in" )
) {
}