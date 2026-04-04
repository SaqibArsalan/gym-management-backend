package com.gym.identity.repository

import com.gym.identity.model.UserRoles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository : JpaRepository<UserRoles, String> {

}