package dev.ymuratov.feature.characters.presentation.model

sealed interface CharactersAction {

    data class NavigateToCharactersInfo(val id: String): CharactersAction
}