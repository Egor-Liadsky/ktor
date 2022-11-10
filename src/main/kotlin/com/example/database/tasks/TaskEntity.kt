package com.example.database.tasks

import com.example.features.tasks.Task
import com.example.features.tasks.Tasks
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object TaskEntity : Table("tasks") {
    private val rowId = integer("id").autoIncrement()
    private val toId = integer("to_id")
    private val fromId = integer("from_id")
    private val title = varchar("title", 50)
    private val text = varchar("text", 1000)
    private val isHidden = bool("is_hidden")
    private val timeStart = varchar("time_start", 50)
    private val timeEnd = varchar("time_end", 50)

    override val primaryKey = PrimaryKey(rowId, name = "id")

    fun insert(taskDTO: TaskDTO) {
        transaction {
            SchemaUtils.create(TaskEntity)
            insert {
                it[fromId] = taskDTO.fromId
                it[toId] = taskDTO.toId
                it[title] = taskDTO.title
                it[text] = taskDTO.text
                it[isHidden] = taskDTO.isHidden
                it[timeStart] = taskDTO.timeStart
                it[timeEnd] = taskDTO.timeEnd
            }
        }
    }

    fun fetchAllTasks(): Tasks {
        val list = ArrayList<Task>()
        val taskEntity = transaction {
            TaskEntity.select { TaskEntity.isHidden.eq(false) }.forEach {
                list.add(
                    Task(
                        id = it[rowId],
                        fromId = it[fromId],
                        toId = it[toId],
                        title = it[title],
                        text = it[text],
                        isHidden = it[isHidden],
                        timeStart = it[timeStart],
                        timeEnd = it[timeEnd],
                    )
                )
            }
        }
        return Tasks(list)
    }

    fun fetchTasksId(id: Int): Task {
        val taskEntity = transaction { TaskEntity.select { TaskEntity.rowId.eq(id) }.single() }
        return Task(
            id = taskEntity[rowId],
            fromId = taskEntity[fromId],
            toId = taskEntity[toId],
            title = taskEntity[title],
            text = taskEntity[text],
            isHidden = taskEntity[isHidden],
            timeStart = taskEntity[timeStart],
            timeEnd = taskEntity[timeEnd],
        )
    }

    fun fetchHiddenTasks(): Tasks {
        val listTask = ArrayList<Task>()
        val taskEntity = transaction {
            TaskEntity.select { TaskEntity.isHidden.eq(true) }.forEach {
                listTask.add(
                    Task(
                        id = it[rowId],
                        fromId = it[fromId],
                        toId = it[toId],
                        title = it[title],
                        text = it[text],
                        isHidden = it[isHidden],
                        timeStart = it[timeStart],
                        timeEnd = it[timeEnd],
                    )
                )
            }
        }
        return Tasks(listTask)
    }
}