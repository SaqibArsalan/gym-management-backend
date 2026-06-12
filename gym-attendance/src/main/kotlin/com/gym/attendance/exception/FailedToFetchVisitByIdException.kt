package com.gym.attendance.exception

class FailedToFetchVisitByIdException(visitId: String): Exception(
    String.format("Failed to fetch Visit by id %s", visitId)
) {
}