package org.example.kmp

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/pets") {
                val pet = Pet(
                    name = "Fluffy",
                    photoUrls = listOf("https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191112123933390_COVER.jpg"),
                    id = null,
                    category = null,
                    status = null,
                )
                call.respond(listOf(pet))
            }
        }
    }.start(wait = true)
}

fun Application.module() {

}