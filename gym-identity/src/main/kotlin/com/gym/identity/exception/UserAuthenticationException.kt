package com.gym.identity.exception

class UserAuthenticationException(): Exception(
    String.format("Bad Authentication Credentials")
) {
}