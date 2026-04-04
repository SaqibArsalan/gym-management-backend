package com.gym.identity.repository

import com.gym.identity.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {

    override fun findAll(): List<User>

    override fun findById(id: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun existsByEmailIgnoreCase(email: String): Boolean

    @Query("SELECT u from User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findUsersByName(@Param("name") name: String): List<User>

}