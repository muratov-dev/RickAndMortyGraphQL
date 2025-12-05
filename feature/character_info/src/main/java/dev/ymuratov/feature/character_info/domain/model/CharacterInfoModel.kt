package dev.ymuratov.feature.character_info.domain.model


import kotlinx.serialization.Serializable

@Serializable
data class CharacterInfoModel(
    val episode: List<EpisodeModel?>,
    val gender: String,
    val id: String,
    val image: String,
    val location: LocationModel?,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String
)