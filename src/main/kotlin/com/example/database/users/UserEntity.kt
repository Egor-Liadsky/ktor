package com.example.database.users

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserEntity : Table("user") {
    private val rowId = integer("id")
    private val login = varchar("login", 25)
    private val email = varchar("email", 25)
    private val password = varchar("password", 25)

    override val primaryKey = PrimaryKey(rowId, name = "id")

    fun registerUser(userDTO: UserDTO) {
        transaction {
            UserEntity.insert {
                it[rowId] = rowId
                it[email] = userDTO.email
                it[login] = userDTO.login
                it[password] = userDTO.password
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val userEntity = UserEntity.select { UserEntity.login.eq(login) }.single()
                UserDTO(
                    login = login,
                    email = userEntity[email],
                    password = userEntity[password]
                )
            }
        }catch (e: Exception) {
            null
        }
    }
}