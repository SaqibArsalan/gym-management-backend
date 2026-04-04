package com.gym.identity.exception

class EmailAlreadyTakenException(email: String): Exception(
    String.format("Email already taken: %s", email)
) {
}