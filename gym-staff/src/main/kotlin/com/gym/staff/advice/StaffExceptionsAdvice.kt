package com.gym.staff.advice

import com.gym.staff.exception.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice(basePackages = ["com.gym.staff"])
class StaffExceptionsAdvice {
    private val logger: Logger = LogManager.getLogger(StaffExceptionsAdvice::class.java)
    private val errorMessage = "An {} exception has occurred, errors : [{}]"
    private val _errorMessage = "An {} exception has occurred, errors : [{}], ex: {}"


    @ExceptionHandler(FailedToCreateStaffException::class)
    fun handlerForFailedToCreateMemberException(ex: FailedToCreateStaffException): ResponseEntity<Any> {
        val errors: MutableList<String> = Collections.singletonList(ex.message)
        logger.error(errorMessage, ex.javaClass.name, errors.joinToString(","), ex);
        return ResponseEntity(
            mapOf("errors" to ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

}