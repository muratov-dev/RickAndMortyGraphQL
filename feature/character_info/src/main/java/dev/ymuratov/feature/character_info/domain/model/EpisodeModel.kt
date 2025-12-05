package dev.ymuratov.feature.character_info.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeModel(
    val airDate: String,
    val episode: String,
    val name: String
)