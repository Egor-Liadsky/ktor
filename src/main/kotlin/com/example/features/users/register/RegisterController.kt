package com.example.features.users.register

import com.example.database.tokens.TokenDTO
import com.example.database.tokens.TokenEntity
import com.example.database.users.UserDTO
import com.example.database.users.UserEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.UUID

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerUser() {
        val registerReceive = call.receive<RegisterReceiveRemote>()

        val token = UUID.randomUUID().toString()

        val userDTO = UserEntity.fetchUser(registerReceive.login)
        if (userDTO != null){
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            try {
                UserEntity.insert(
                    UserDTO(
                        login = registerReceive.login,
                        email = registerReceive.email,
                        password = registerReceive.password
                    )
                )
            } catch (e: ExposedSQLException){
                call.respond("Error Exposed")
            } catch (e: Exception){
                call.respond("Error Exception: ${e.localizedMessage}")
            }

            TokenEntity.insert(TokenDTO(login = registerReceive.login, token = token))

            call.respond(HttpStatusCode.Created, RegisterResponseRemote(token = token))
        }
    }
}