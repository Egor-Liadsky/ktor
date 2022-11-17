package com.example.database

import com.example.database.tokens.TokenEntity
import com.example.database.users.UserEntity
import com.example.utils.Settings
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase(config: ApplicationConfig) {
    val driverClassName = "org.postgresql.Driver"
    val jdbcURL = "jdbc:postgresql://localhost:5432/${Settings.databaseName}"
    val user = Settings.userDb
    val password = Settings.passwordDb
    val database = Database.connect(jdbcURL, driverClassName, user = user, password = password)
    transaction(database) {
        SchemaUtils.create(UserEntity)
        SchemaUtils.create(TokenEntity)
    }
}