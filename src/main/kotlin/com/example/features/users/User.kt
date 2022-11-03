package com.example.features.users

import kotlinx.serialization.Serializable

@Serializable
data class UserReceiveRemote(
    val login: String,
    val password: String
)

@Serializable
data class UserResponseRemote(
    val id: Int,
    val login: String,
    val password: String
)
