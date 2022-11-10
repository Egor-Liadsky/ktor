package com.example.features.tasks

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column

@Serializable
data class TaskReceive(
    val fromId: Int,
    val toId: Int,
    val title: String,
    val text: String,
    val isHidden: Boolean,
    val timeStart: String,
    val timeEnd: String
)

@Serializable
data class Tasks(
    val tasks: List<Task>
)

@Serializable
data class Task(
    val id: Int,
    val fromId: Int,
    val toId: Int,
    val title: String,
    val text: String,
    val isHidden: Boolean,
    val timeStart: String,
    val timeEnd: String
)

@Serializable
data class SelectTask(val id: Int)