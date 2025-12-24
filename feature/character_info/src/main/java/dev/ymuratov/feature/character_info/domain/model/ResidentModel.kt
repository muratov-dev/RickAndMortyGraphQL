package dev.ymuratov.feature.character_info.domain.model


import kotlinx.serialization.Serializable

@Serializable
data class ResidentModel(
    val id: String,
    val name: String,
    val image: String
)