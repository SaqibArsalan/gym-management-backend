package com.gym.identity.exception

class UserNotPresentException(userId: String): Exception(
    String.format("Cannot find user with userId: %s", userId)
) {
}