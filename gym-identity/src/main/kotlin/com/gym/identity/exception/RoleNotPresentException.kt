package com.gym.identity.exception

class RoleNotPresentException(roleId: String): Exception(
    String.format("Cannot find role with roleId: %s", roleId)
) {
}