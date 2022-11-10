package com.example.features.users.register

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.registerRouting(){
    post ("/register"){
        val registerController = RegisterController(call)
        registerController.registerUser()
    }
}