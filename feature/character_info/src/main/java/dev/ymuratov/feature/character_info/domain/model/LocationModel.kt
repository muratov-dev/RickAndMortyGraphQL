package dev.ymuratov.feature.character_info.domain.model


import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    val dimension: String,
    val name: String,
    val residents: List<ResidentModel?>,
    val type: String
)