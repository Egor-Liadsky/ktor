package com.example.features.users.register

import com.example.database.tokens.TokenDTO
import com.example.database.tokens.TokenEntity
import com.example.database.users.UserDTO
import com.example.database.users.UserEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.SchemaUtils
import java.util.*

class RegisterController(private val call: ApplicationCall) {
    suspend fun registerUser() {
        val receive = call.receive<RegisterReceiveRemote>()
        val userDTO = UserEntity.fetchUser(receive.login)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                UserEntity.registerUser(
                    UserDTO(
                        login = receive.login,
                        email = receive.email,
                        password = receive.password
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }
            TokenEntity.insert(TokenDTO(
                login = receive.login,
                token = token
            ))
            call.respond(HttpStatusCode.Created, RegisterResponseRemote(token))
        }
    }
}