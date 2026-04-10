package com.gym.com.gym.classes.repository

import com.gym.com.gym.classes.model.Enrollment
import com.gym.com.gym.classes.model.GymClass
import com.gym.com.gym.classes.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EnrollmentRepository : JpaRepository<Enrollment, String> {

    @Query("""
        SELECT COUNT(e) FROM Enrollment e 
        WHERE e.session.id = :sessionId 
        AND e.status = 'enrolled'
    """)
    fun countActiveEnrollmentsBySessionId(sessionId: String): Long

    fun existsBySessionIdAndMembershipId(sessionId: String, membershipId: String): Boolean

    fun findAllBySessionId(sessionId: String): List<Enrollment>

    fun findAllByUserId(userId: String): List<Enrollment>
}