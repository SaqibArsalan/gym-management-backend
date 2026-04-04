package com.gym.com.gym.classes.service

import com.gym.com.gym.classes.Repository.ClassManagementRepository
import com.gym.com.gym.classes.Repository.SessionManagementRepository
import com.gym.com.gym.classes.controller.dto.ClassResponseDto
import com.gym.com.gym.classes.controller.dto.SessionCreateOrUpdateDto
import com.gym.com.gym.classes.controller.dto.SessionResponseDto
import com.gym.com.gym.classes.exception.FailedToCreateSessionException
import com.gym.com.gym.classes.exception.FailedToFetchAllClassesException
import com.gym.com.gym.classes.exception.FailedToFetchClassForIdException
import com.gym.com.gym.classes.model.Session
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionManagementService(private val sessionRepository: SessionManagementRepository,
                                private val classManagementRepository: ClassManagementRepository) {

    @Transactional
    fun createSessionForClass(sessionCreateOrUpdateDto: SessionCreateOrUpdateDto, classId: String): SessionResponseDto {
        try {
            val gymClass = classManagementRepository.findById(classId).orElseThrow {
                FailedToFetchClassForIdException(classId)
            }
            val savedClass = sessionRepository.save(Session.createFrom(sessionCreateOrUpdateDto, gymClass))
            return SessionResponseDto.createFrom(savedClass)

        } catch (ex: Exception) {
            throw FailedToCreateSessionException()
        }
    }

    fun getAllSessions(): List<SessionResponseDto> {
        try {
            val sessions = sessionRepository.findAll()
            return sessions.map { SessionResponseDto.createFrom(it) }
        } catch (ex: Exception) {
            throw FailedToFetchAllClassesException()
        }
    }

//    fun getClassDetails(id: String): ClassDto {
//        try {
//            val classModel = classRepository.findById(id).orElseThrow { FailedToFetchClassDetailsException(id)  }
//            return ClassDto.createFrom(classModel)
//        } catch (ex: FailedToFetchClassDetailsException) {
//            throw FailedToFetchClassDetailsException(id)
//        }
//    }

}