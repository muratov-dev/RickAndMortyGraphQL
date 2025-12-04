package dev.ymuratov.feature.characters.presentation.model

sealed interface CharactersEvent {

    data class OnSearchQueryChanged(val name: String) : CharactersEvent
    data class OnCharacterCardClick(val id: String) : CharactersEvent
}