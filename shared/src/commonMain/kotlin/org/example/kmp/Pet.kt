package org.example.kmp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val id: Int?,
    val name: String,
    val category: Category?,
    val photoUrls: List<String>,
    val status: Status?
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
    val id: Int?,
    val name: String?,
)
