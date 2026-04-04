package com.gym.identity.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gym.identity.controller.dto.UserInfoDto
import jakarta.persistence.*
import java.sql.Timestamp


@Entity
@Table(name = "users")
data class User(
    @Column(name = "email")
    val email: String = "",

    @Column(name = "password")
    val password: String = "",

    @Column(name = "first_name")
    val firstName: String = "",

    @Column(name = "last_name")
    var lastName: String = "",

    @Column(name = "phone_number")
    val phoneNumber: String = "",

    @Column(name = "date_of_birth")
    val dateOfBirth: Timestamp = Timestamp.valueOf("1970-01-01 05:01:01"),

    @Column(name = "account_status")
    val accountStatus: String = "",

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [ JoinColumn(name = "user_id")] ,
        inverseJoinColumns = [ JoinColumn(name = "role_id")]
    )
    var roles: Set<Role> = mutableSetOf()

) : BaseEntity() {

    fun toUserInfo() : UserInfoDto {
        return UserInfoDto(
            id = id!!,
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            phoneNumber = this.phoneNumber,
            accountStatus = accountStatus
        )
    }
}



