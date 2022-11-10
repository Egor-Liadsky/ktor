package com.example.features.users.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.loginRouting(){
    post("/login") {
        val loginController = LoginController(call)
        loginController.performLogin()
    }
}