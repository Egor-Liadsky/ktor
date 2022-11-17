package com.example.features.users.login

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.client.methods.RequestBuilder.post

fun Application.loginRouting(){
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}