package com.example.database.users

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserEntity : IntIdTable("users") {
    private val login = varchar("login", 25)
    private val password = varchar("password", 25)

    fun insertNewUser(userDTO: UserDTO) {
        transaction {
            addLogger(StdOutSqlLogger)
             UserEntity.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
            }
        }
    }

    fun fetchAllUsers(){
        transaction {
            addLogger(StdOutSqlLogger)
            UserEntity.select { UserEntity.id eq 1}.first()
        }
    }
}