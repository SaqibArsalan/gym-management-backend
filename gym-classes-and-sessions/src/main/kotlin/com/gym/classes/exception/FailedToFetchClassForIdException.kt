package com.gym.com.gym.classes.exception

class FailedToFetchClassForIdException(classId: String): Exception(
    String.format("Failed to find class for id %s", classId )
) {
}