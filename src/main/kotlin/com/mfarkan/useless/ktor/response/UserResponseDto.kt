package com.mfarkan.useless.ktor.response

import com.mfarkan.useless.ktor.domain.enums.UserType
import java.util.*

data class UserResponseDto(val id: UUID, val userName: String, val userType: UserType, val userMail: String)