package com.gym.staff.repository

import com.gym.staff.model.Staff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StaffRepository: JpaRepository<Staff, String> {

    fun findByUserId(userId: String): Staff

    @Query("SELECT s from Staff s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findUsersByName(@Param("name") name: String): List<Staff>


}