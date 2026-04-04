package com.gym.identity.exception

class FailedToFetchUsersException(): Exception(
    String.format("Failed to find Users")
) {
}