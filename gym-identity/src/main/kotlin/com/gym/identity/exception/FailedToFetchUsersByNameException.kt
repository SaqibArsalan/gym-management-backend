package com.gym.identity.exception

class FailedToFetchUsersByNameException(name: String): Exception(
    String.format("Failed to fine find user with name: %s", name)
) {
}