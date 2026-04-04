package com.gym.identity.model

import jakarta.persistence.*


@Entity
@Table(name = "`user_roles`")
data class UserRoles(
    @Column(name = "user_id")
    val userId: String = "",
    @Column(name = "role_id")
    val roleId: String = "",

) : BaseEntity()



