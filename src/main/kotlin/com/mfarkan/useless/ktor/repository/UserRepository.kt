package com.mfarkan.useless.ktor.repository

import com.mfarkan.useless.ktor.domain.User
import com.mfarkan.useless.ktor.exception.UserNotFoundException
import com.mfarkan.useless.ktor.request.UserCreateRequestDto
import com.mfarkan.useless.ktor.request.UserUpdateRequestDto
import com.mfarkan.useless.ktor.response.UserResponseDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.util.*

class UserRepository {
    suspend fun saveUser(request: UserCreateRequestDto): UUID {
        val userId = suspendedTransactionAsync(context = Dispatchers.IO) {
            User.insertAndGetId {
                it[userMail] = request.userMail
                it[userType] = request.userType
                it[userName] = request.userName
            }.value
        }
        return userId.await()
    }

    suspend fun deleteUser(id: UUID) {
        suspendedTransactionAsync(context = Dispatchers.IO) {
            User.deleteWhere {
                User.id eq id
            }
        }
    }

    suspend fun getUser(id: UUID): UserResponseDto? {
        val userResponse = suspendedTransactionAsync(context = Dispatchers.IO) {
            User.select {
                User.id eq id
            }.map { mapToResponseDTO(it) }.firstOrNull()
        }
        val user = userResponse.await()
        return user ?: throw UserNotFoundException()
    }

    suspend fun updateUser(id: UUID, request: UserUpdateRequestDto) {
        suspendedTransactionAsync(context = Dispatchers.IO) {
            User.update({ User.id eq id }) {
                it[userMail] = request.userMail
                it[userName] = request.userName
                it[userType] = request.userType
            }
        }
    }

    suspend fun getAllUser(page: Long, pageSize: Int): List<UserResponseDto> {
        var result = suspendedTransactionAsync(context = Dispatchers.IO) {
            User.selectAll().limit(pageSize, page).map { mapToResponseDTO(it) }
        }
        return result.await();
    }
}

fun mapToResponseDTO(result: ResultRow): UserResponseDto =
    UserResponseDto(
        result[User.id].value,
        result[User.userName],
        result[User.userType],
        result[User.userMail]
    )