package com.example.plugins

import com.example.features.tasks.taskRouting
import com.example.features.users.userRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        userRouting()
        taskRouting()
    }
}
