package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.utils.Constants
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = 8082, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/${Constants.databaseName}",
        driver = "org.postgresql.Driver",
        user = Constants.userDb, password = Constants.passwordDb
    )

    configureSerialization()
    configureRouting()
}
