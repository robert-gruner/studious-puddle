package org.example.kmp.pets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetEntities(val entities: List<Pet>)

@Serializable
data class Pet(
    val id: Int,
    val name: String,
    val category: Category,
    val photoUrls: List<String>,
    val status: Status
)

@Serializable
enum class Status {
    @SerialName("available")
    AVAILABLE,

    @SerialName("pending")
    PENDING,

    @SerialName("sold")
    SOLD,

}

@Serializable
data class Category(
    val id: Int,
    val name: String,
)
