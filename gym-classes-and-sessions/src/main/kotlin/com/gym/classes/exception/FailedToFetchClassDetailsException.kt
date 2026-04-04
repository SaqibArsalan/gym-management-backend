package com.gym.com.gym.classes.exception

class FailedToFetchClassDetailsException(id: String): Exception(
    String.format("Failed to fetch Class Details for id %s", id )
) {
}