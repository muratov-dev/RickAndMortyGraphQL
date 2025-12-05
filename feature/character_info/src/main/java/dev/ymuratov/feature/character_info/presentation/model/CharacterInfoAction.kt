package dev.ymuratov.feature.character_info.presentation.model

sealed interface CharacterInfoAction {

    data object NavigateUp : CharacterInfoAction
}