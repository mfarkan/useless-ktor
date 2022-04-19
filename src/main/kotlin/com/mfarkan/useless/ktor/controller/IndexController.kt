package com.mfarkan.useless.ktor.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.indexRoute() {
    route("/index") {

        indexController()
    }
}

fun Route.indexController() {
    route("") {
        get {
            call.respond(status = HttpStatusCode.OK, "Hello World")
        }
    }
}