package com.gym.classes.repository


import com.gym.com.gym.classes.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionManagementRepository: JpaRepository<Session, String> {
}