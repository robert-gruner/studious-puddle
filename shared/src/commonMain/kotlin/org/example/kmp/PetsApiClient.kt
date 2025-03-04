package org.example.kmp

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.engine.cio.*

class PetsApiClient {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchPets(): List<Pet> {
        return httpClient.get("http://localhost:${SERVER_PORT}/pets").body()
    }
}