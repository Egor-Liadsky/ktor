package com.example.database.tasks

import kotlinx.serialization.Serializable

@Serializable
data class TaskDTO(
    val fromId: Int,
    val toId: Int,
    val title: String,
    val text: String,
    val isHidden: Boolean,
    val timeStart: String,
    val timeEnd: String
)