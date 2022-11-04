package com.example.features.tasks

import com.example.database.tasks.TaskDTO
import com.example.database.tasks.TaskEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class TaskController(private val call: ApplicationCall) {

    suspend fun newTask() {
        val taskReceive = call.receive<TaskReceive>()

        TaskEntity.insert(
            TaskDTO(
                fromId = taskReceive.fromId,
                toId = taskReceive.toId,
                title = taskReceive.title,
                text = taskReceive.text,
                isHidden = taskReceive.isHidden,
                timeStart = taskReceive.timeStart,
                timeEnd = taskReceive.timeEnd
            )
        )
        call.respond(HttpStatusCode.Created, "Task created")
    }

    suspend fun fetchTask(id: Int) {
        call.respond(HttpStatusCode.OK, TaskEntity.fetchTasks(id))
    }

    suspend fun fetchHiddenTask(){
        call.respond(HttpStatusCode.OK, TaskEntity.fetchHiddenTasks())
    }
}