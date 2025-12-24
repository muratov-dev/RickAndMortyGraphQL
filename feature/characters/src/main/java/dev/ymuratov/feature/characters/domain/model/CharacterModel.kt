package dev.ymuratov.feature.characters.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val gender: String,
    val id: String,
    val location: String,
    val name: String,
    val species: String,
    val type: String,
    val status: String,
    val image: String
)