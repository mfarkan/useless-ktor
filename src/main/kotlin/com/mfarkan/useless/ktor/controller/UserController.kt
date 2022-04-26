package com.mfarkan.useless.ktor.controller

import com.mfarkan.useless.ktor.repository.UserRepository
import com.mfarkan.useless.ktor.request.UserCreateRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute(userRepository: UserRepository) {
    route("/user") {
        userController(userRepository)
    }
}

fun Route.userController(userRepository: UserRepository) {
    route("") {
        post {
            val request = call.receive<UserCreateRequestDto>()
            println("route post ${Thread.currentThread().name}")
            userRepository.saveUser(request)
            call.response.status(HttpStatusCode.Created)
        }
        get {
            val users = userRepository.getAllUser(0, 10)
            call.respond(message = users, status = HttpStatusCode.OK)
        }
    }
}