package com.example.features.users.login

import com.example.database.tokens.TokenDTO
import com.example.database.tokens.TokenEntity
import com.example.database.users.UserEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){
        val loginReceive = call.receive<LoginReceiveRemote>()
        val userDTO = UserEntity.fetchUser(loginReceive.login)

        if (userDTO == null){
            call.respond(HttpStatusCode.NotFound, "User not found")
        } else {
            if (userDTO.password == loginReceive.password){
                val token = UUID.randomUUID().toString()
                TokenEntity.insert(TokenDTO(login = loginReceive.login, token = token))

                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}