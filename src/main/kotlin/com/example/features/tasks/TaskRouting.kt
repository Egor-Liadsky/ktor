package com.example.features.tasks

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.taskRouting() {
    post("/new_task") {
        val taskController = TaskController(call)
        taskController.newTask()
    }

    get("/tasks ") {
        val taskController = TaskController(call)
        taskController.fetchAllTask()
    }

    get ("/tasks/hidden"){
        val taskController = TaskController(call)
        taskController.fetchHiddenTask()
    }

    get("/tasks/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing id")
        val taskController = TaskController(call)
        taskController.fetchTaskId(id.toInt())
    }
}