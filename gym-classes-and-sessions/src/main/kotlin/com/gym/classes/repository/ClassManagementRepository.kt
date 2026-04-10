package com.gym.classes.repository

import com.gym.com.gym.classes.model.GymClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClassManagementRepository: JpaRepository<GymClass, String> {

    @Query("SELECT c from GymClass c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findClassesByName(@Param("name") name: String): List<GymClass>

}