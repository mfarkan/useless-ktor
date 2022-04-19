package com.mfarkan.useless.ktor

import com.mfarkan.useless.ktor.controller.indexRoute
import com.mfarkan.useless.ktor.controller.userRoute
import com.mfarkan.useless.ktor.plugins.configureHTTP
import com.mfarkan.useless.ktor.plugins.configureMonitoring
import com.mfarkan.useless.ktor.plugins.configureSerialization
import com.mfarkan.useless.ktor.plugins.configureStatusPage
import com.mfarkan.useless.ktor.repository.UserRepository
import com.mfarkan.useless.ktor.service.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    configureSerialization()
    configureHTTP()
    configureMonitoring()

    configureStatusPage()

    DatabaseFactory().init()
    val userRepository = UserRepository()
    routing {
        route("api") {
            indexRoute()
            userRoute(userRepository)
        }
    }
}
