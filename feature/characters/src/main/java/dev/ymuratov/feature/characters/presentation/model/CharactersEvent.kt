package dev.ymuratov.feature.characters.presentation.model

sealed interface CharactersEvent {

    data class OnSearchValueUpdate(val name: String): CharactersEvent
    data class OnCharacterClick(val id: String): CharactersEvent
}