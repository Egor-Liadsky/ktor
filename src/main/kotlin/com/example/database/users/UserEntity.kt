package com.example.database.users

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserEntity : IntIdTable("users"){
    private val login = varchar("login", 25)
    private val email = varchar("email", 25)
    private val password = varchar("password", 25)


    fun insert(userDTO: UserDTO){
        transaction {
            SchemaUtils.create(UserEntity)
            addLogger(StdOutSqlLogger)
            UserEntity.insert {
                it[login] = userDTO.login
                it[email] = userDTO.email
                it[password] = userDTO.password
            }
        }
    }

    fun fetchUser(login: String):UserDTO?{
        return try {
            transaction {
                val userEntity = UserEntity.select { UserEntity.login.eq(login) }.single()
                UserDTO(
                    login = userEntity[UserEntity.login],
                    email = userEntity[UserEntity.email],
                    password = userEntity[UserEntity.password]
                )
            }
        }catch (e: Exception){
            null
        }
    }
}