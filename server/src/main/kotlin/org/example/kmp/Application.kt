package org.example.kmp

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.commands.jsonGet
import io.ktor.http.HttpStatusCode
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

        get("/pets/{id}") {
            try {
                val petId = call.parameters["id"]
                require(petId != null) { "Pet does not exist" }
                val pets = redisClient.jsonGet("pets", "$.entities[?(@.id==${petId})]") ?: "[]"
                val matches = Json.decodeFromString<List<Pet>>(pets)

                require(matches.isNotEmpty()) { "Pet does not exist" }
                call.respond(matches[0])
            } catch (e: IllegalArgumentException) {
                call.response.status(HttpStatusCode.BadRequest)
            }

        }
    }
}