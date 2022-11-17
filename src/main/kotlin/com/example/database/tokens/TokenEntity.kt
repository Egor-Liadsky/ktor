package com.example.database.tokens

import com.example.database.users.UserEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object TokenEntity : IntIdTable("tokens") {
    private val login = varchar("login", 25)
    private val token = varchar("token", 70)


    fun insert(tokenDTO: TokenDTO) {
        transaction {
            SchemaUtils.create(TokenEntity)
            TokenEntity.insert {
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                TokenEntity.selectAll().toList()
                    .map {
                        TokenDTO(
                            login = it[TokenEntity.login],
                            token = it[TokenEntity.token]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
