package com.mfarkan.useless.ktor.plugins

import com.mfarkan.useless.ktor.exception.UserNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<UserNotFoundException> { call, _ ->
            call.respond(HttpStatusCode.NotFound)
        }
    }
}