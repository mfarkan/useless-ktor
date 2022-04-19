package com.mfarkan.useless.ktor.domain

import com.mfarkan.useless.ktor.domain.enums.UserType
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object User : UUIDTable("users") {
    var userName: Column<String> = varchar(name = "user_name", length = 150).index()
    var userMail: Column<String> = varchar(name = "user_mail", length = 50)
    var userType: Column<UserType> = enumeration("type", UserType::class)
}