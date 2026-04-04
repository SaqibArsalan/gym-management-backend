package com.gym.identity.service

import com.gym.identity.controller.dto.UserAddOrUpdateDto
import com.gym.identity.controller.dto.UserInfoDto
import com.gym.identity.exception.EmailAlreadyTakenException
import com.gym.identity.exception.FailedToFetchUsersByNameException
import com.gym.identity.exception.FailedToFetchUsersException
import com.gym.identity.exception.UserNotPresentException
import com.gym.identity.model.User
import com.gym.identity.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(private val userRepository: UserRepository,
                   private val passwordEncoder: PasswordEncoder)
{

    fun getUsers(): List<UserInfoDto> {
        try {
            val users = userRepository.findAll()
            return users.map {
                UserInfoDto.createFrom(it)
            }

        } catch (ex: Exception) {
            throw FailedToFetchUsersException()
        }
    }

    fun getUser(userId: String): UserInfoDto {
        try {
            val user = userRepository.findById(userId)
            if (user.isPresent) {
                return user.get().toUserInfo()
            } else {
                throw UserNotPresentException(userId)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Transactional
    fun addUser(addUserAddOrUpdateDto: UserAddOrUpdateDto): UserInfoDto {
        ensureEmailIsAvailable(addUserAddOrUpdateDto.email)
        val userModel: User = addUserAddOrUpdateDto.toUserModel(passwordEncoder)
        val savedUser = userRepository.save(userModel)
        return savedUser.toUserInfo()
    }

    private fun ensureEmailIsAvailable(email: String) {
        if(userRepository.existsByEmailIgnoreCase(email)) {
            throw EmailAlreadyTakenException(email)
        }
    }

    fun ensureUserIsAvailable(userId: String): Boolean {
        return userRepository.existsById(userId)
    }

    fun searchUsers(name: String): List<UserInfoDto> {
        try {
            val users = userRepository.findUsersByName(name)
            return users.map { UserInfoDto.createFrom(it) }
        } catch (ex: FailedToFetchUsersByNameException) {
            throw FailedToFetchUsersByNameException(name)
        }
    }
}