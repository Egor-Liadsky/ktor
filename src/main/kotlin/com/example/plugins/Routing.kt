package com.example.plugins

import com.example.features.tasks.taskRouting
import com.example.features.users.login.loginRouting
import com.example.features.users.register.registerRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        registerRouting()
        loginRouting()
        taskRouting()
    }
}
