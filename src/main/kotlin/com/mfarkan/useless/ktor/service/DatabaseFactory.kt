package com.mfarkan.useless.ktor.service

import com.mfarkan.useless.ktor.domain.User
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory {
    fun init() {
        Database.connect(hikariDataSource())
        transaction {
            SchemaUtils.createDatabase()
            SchemaUtils.create(tables = arrayOf(User), inBatch = true)
        }
    }

    private fun hikariDataSource(): HikariDataSource {
        var config = HoconApplicationConfig(ConfigFactory.load())
        var hikariDataSource = HikariDataSource()
        hikariDataSource.password = config.property("ktor.security.password").getString()
        hikariDataSource.jdbcUrl = config.property("ktor.security.connection-string").getString()
        hikariDataSource.username = config.property("ktor.security.userName").getString()
        hikariDataSource.schema = "public"
        hikariDataSource.driverClassName = "org.postgresql.Driver"
        return hikariDataSource
    }
}