package com.example.features.users.register

import com.example.features.users.login.LoginController
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.apache.http.client.methods.RequestBuilder.post

fun Application.registerRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerUser()
        }
    }
}