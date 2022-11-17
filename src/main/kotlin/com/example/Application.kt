package com.example

import com.example.features.users.login.loginRouting
import com.example.features.users.register.registerRouting
import io.ktor.server.application.*
import com.example.plugins.*
import com.example.database.initDatabase

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
//    Database.connect(
//        "jdbc:postgresql://localhost:5432/${Settings.databaseName}",
//        driver = "org.postgresql.Driver",
//        user = Settings.userDb, password = Settings.passwordDb
//    )
    initDatabase(environment.config)
    loginRouting()
    registerRouting()
    configureSerialization()
    configureRouting()
}
