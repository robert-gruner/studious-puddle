package org.example.kmp

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.commands.jsonSet
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

val pets: List<Pet> = listOf(
    Pet(
        name = "Fluffy",
        photoUrls = listOf("https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191112123933390_COVER.jpg"),
        id = 1,
        category = Category(1, "Test"),
        status = Status.AVAILABLE,
    ),
    Pet(
        name = "Diego",
        photoUrls = listOf("https://images.dog.ceo/breeds/hound-english/n02089973_3480.jpg"),
        id = 2,
        category = Category(1, "Test"),
        status = Status.AVAILABLE,
    ),
)

fun createSeedingPlugin(redisClient: ReThis): ApplicationPlugin<Unit> {
    return createApplicationPlugin(name = "SeedingPlugin") {
        on(MonitoringEvent(ApplicationStarted)) { application ->
            application.log.info("Seeding starting...")
            runBlocking {
                val entities = PetEntities(pets)
                redisClient.jsonSet(
                    key = "pets", Json.encodeToString(entities)
                )

            }
            application.log.info("Seeding finished...")
        }
    }
}
