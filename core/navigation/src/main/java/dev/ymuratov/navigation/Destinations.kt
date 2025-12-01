package dev.ymuratov.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data object CharactersScreen : Destination()

    @Serializable
    data class CharacterInfoScreen(val id: String) : Destination()
}