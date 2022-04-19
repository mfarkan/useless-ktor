package com.mfarkan.useless.ktor.request

import com.mfarkan.useless.ktor.domain.enums.UserType

data class UserUpdateRequestDto(val userName: String, val userType: UserType, val userMail: String) {
}