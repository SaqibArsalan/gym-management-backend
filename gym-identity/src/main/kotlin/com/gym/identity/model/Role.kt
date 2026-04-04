package com.gym.identity.model

import com.gym.identity.controller.dto.RoleInfoDto
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode

import org.hibernate.type.SqlTypes


@Entity
@Table(name = "`role`")
data class Role(
    @Column(name = "name")
    val name: String = "",

    @Column(name = "description")
    val description: String = "",

    @Column(name = "status")
    val status: String = "",

    @Column(name = "scopes", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val scopes: List<String> = listOf()

) : BaseEntity() {

    fun toRoleInfo(): RoleInfoDto {
        return RoleInfoDto(
            name = name,
            status = status,
            scopes = scopes
        )
    }
}



