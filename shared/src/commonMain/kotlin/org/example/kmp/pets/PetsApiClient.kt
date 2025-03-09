package org.example.kmp.pets

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.engine.cio.*
import org.example.kmp.SERVER_PORT

class PetsApiClient {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchPets(): List<Pet> {
        return httpClient.get("http://localhost:$SERVER_PORT/pets").body()
    }

    suspend fun fetchPet(id: Int): Pet {
        return httpClient.get("http://localhost:$SERVER_PORT/pets/$id").body()
    }
}