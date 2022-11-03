package com.example.database.tasks

import com.example.features.tasks.SelectTask
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object TaskEntity : IntIdTable("tasks"){
    private val toId = integer("to_id")
    private val fromId = integer("from_id")
    private val title = varchar("title", 50)
    private val text = varchar("text", 1000)
    private val isHidden = bool("is_hidden")
    private val timeStart = varchar("time_start", 50)
    private val timeEnd = varchar("time_end", 50)

    fun insert(taskDTO: TaskDTO){
        transaction {
            SchemaUtils.create(TaskEntity)
            insert {
                it[fromId]= taskDTO.fromId
                it[toId] = taskDTO.toId
                it[title] = taskDTO.title
                it[text] = taskDTO.text
                it[isHidden] = taskDTO.isHidden
                it[timeStart] = taskDTO.timeStart
                it[timeEnd] = taskDTO.timeEnd
            }
        }
    }

    fun fetchTasks(id: Int): TaskDTO {
        val taskEntity = transaction { TaskEntity.select { TaskEntity.id.eq(id) }.single() }
        return TaskDTO(
            fromId =taskEntity[fromId],
            toId = taskEntity[toId],
            title = taskEntity[title],
            text = taskEntity[text],
            isHidden = taskEntity[isHidden],
            timeStart = taskEntity[timeStart],
            timeEnd = taskEntity[timeEnd],
        )
    }
}