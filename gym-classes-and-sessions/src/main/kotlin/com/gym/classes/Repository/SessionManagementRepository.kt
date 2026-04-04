package com.gym.com.gym.classes.Repository

import com.gym.com.gym.classes.model.GymClass
import com.gym.com.gym.classes.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionManagementRepository: JpaRepository<Session, String> {
}