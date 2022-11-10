package com.example.features.users

import com.example.database.users.UserDTO
import com.example.database.users.UserEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

//class UserController(private val call: ApplicationCall) {
//    suspend fun registerUser() {
//        val receive = call.receive<UserReceiveRemote>()
//
//        UserEntity.insertNewUser(
//            UserDTO(
//                login = receive.login,
//                password = receive.password
//            )
//        )
//        call.respond(HttpStatusCode.Created, "User created")
//    }
//
//    suspend fun fetchAllUsers() {
//        call.respond(UserEntity.fetchAllUsers())
//    }
//}