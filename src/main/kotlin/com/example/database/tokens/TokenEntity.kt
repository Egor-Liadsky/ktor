package com.example.database.tokens

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object TokenEntity : Table("tokens") {
    private val rowId = integer("id")
    private val login = varchar("login", 25)
    private val token = varchar("token", 70)

    override val primaryKey = PrimaryKey(rowId, name = "id")

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            TokenEntity.insert {
                it[rowId] = rowId
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                TokenEntity.selectAll().toList().map {
                    TokenDTO(
                        token = it[token],
                        login = it[login]
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}