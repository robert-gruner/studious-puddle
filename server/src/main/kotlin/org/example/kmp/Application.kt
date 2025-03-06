package org.example.kmp

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.commands.jsonGet
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val redisClient = ReThis()

    install(createSeedingPlugin(redisClient))
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/pets") {
            val pets = redisClient.jsonGet("pets") ?: "{}"
            call.respond(Json.decodeFromString<PetEntities>(pets).entities)
        }
    }
}