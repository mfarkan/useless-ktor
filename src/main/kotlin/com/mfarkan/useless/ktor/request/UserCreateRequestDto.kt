package com.mfarkan.useless.ktor.request

import com.mfarkan.useless.ktor.domain.enums.UserType

data class UserCreateRequestDto(val userName: String, val userType: UserType, val userMail: String) {
}