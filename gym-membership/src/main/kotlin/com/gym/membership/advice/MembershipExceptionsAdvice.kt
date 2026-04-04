package com.gym.membership.advice


import com.gym.membership.exception.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice(basePackages = ["com.gym.membership"])
class MembershipExceptionsAdvice {
    private val logger: Logger = LogManager.getLogger(MembershipExceptionsAdvice::class.java)
    private val errorMessage = "An {} exception has occurred, errors : [{}]"
    private val _errorMessage = "An {} exception has occurred, errors : [{}], ex: {}"


    @ExceptionHandler(FailedToCreateMemberException::class)
    fun handlerForFailedToCreateMemberException(ex: FailedToCreateMemberException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToCreateMembershipPlanException::class)
    fun handlerForFailedToCreateMembershipPlanException(ex: FailedToCreateMembershipPlanException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToFetchMembershipPlanForIdException::class)
    fun handlerForFailedToFetchMembershipPlanForIdException(ex: FailedToFetchMembershipPlanForIdException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToFetchMembershipPlansException::class)
    fun handlerForFailedToFetchMembershipPlanException(ex: FailedToFetchMembershipPlansException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToFetchMembershipSubscriptionsException::class)
    fun handlerForFailedToFetchMembershipSubscriptionsException(ex: FailedToFetchMembershipSubscriptionsException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(FailedToFetchMembershipDetailsForIdException::class)
    fun handlerForFailedToFetchMembershipDetailsForIdException(ex: FailedToFetchMembershipDetailsForIdException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

}