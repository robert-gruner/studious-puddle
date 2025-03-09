package org.example.kmp

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.commands.jsonSet
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.example.kmp.pets.Category
import org.example.kmp.pets.Pet
import org.example.kmp.pets.PetEntities
import org.example.kmp.pets.Status

object AvailableCategory {
    val small = Category(1, "Small")
    val big = Category(2, "Big")
}

val pets: List<Pet> = listOf(
    Pet(
        name = "Fluffy",
        photoUrls = listOf("https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191112123933390_COVER.jpg"),
        id = 1,
        category = AvailableCategory.small,
        status = Status.AVAILABLE,
    ),
    Pet(
        name = "Diego",
        photoUrls = listOf("https://images.dog.ceo/breeds/hound-english/n02089973_3480.jpg"),
        id = 2,
        category = AvailableCategory.big,
        status = Status.AVAILABLE,
    ),
    Pet(
        name = "Cortez",
        photoUrls = listOf("https://images.dog.ceo/breeds/australian-kelpie/IMG_4918.jpg"),
        id = 3,
        category = AvailableCategory.big,
        status = Status.PENDING,
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
