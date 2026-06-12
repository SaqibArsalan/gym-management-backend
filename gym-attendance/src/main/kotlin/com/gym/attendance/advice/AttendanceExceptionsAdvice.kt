package com.gym.attendance.advice

import com.gym.attendance.exception.AlreadyCheckedOutException
import com.gym.attendance.exception.FailedToCreateCheckInException
import com.gym.attendance.exception.FailedToFetchMembershipForUserException
import com.gym.com.gym.attendance.exception.NoActiveMembershipException
import com.gym.com.gym.attendance.exception.UserAlreadyCheckedInException
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice(basePackages = ["com.gym.attendance"])
class AttendanceExceptionsAdvice {
    private val logger: Logger = LogManager.getLogger(AttendanceExceptionsAdvice::class.java)
    private val errorMessage = "An {} exception has occurred, errors : [{}]"
    private val _errorMessage = "An {} exception has occurred, errors : [{}], ex: {}"


    @ExceptionHandler(FailedToCreateCheckInException::class)
    fun handlerForFailedToCreateMemberException(ex: FailedToCreateCheckInException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(UserAlreadyCheckedInException::class)
    fun handlerForUserAlreadyCheckedInException(ex: UserAlreadyCheckedInException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(NoActiveMembershipException::class)
    fun handlerForNoActiveMembershipException(ex: NoActiveMembershipException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToFetchMembershipForUserException::class)
    fun handlerForFailedToFetchMembershipForUserException(ex: FailedToFetchMembershipForUserException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(AlreadyCheckedOutException::class)
    fun handlerForAlreadyCheckedOutException(ex: AlreadyCheckedOutException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex)
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

}