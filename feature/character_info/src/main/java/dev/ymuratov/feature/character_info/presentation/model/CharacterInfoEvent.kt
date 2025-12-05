package dev.ymuratov.feature.character_info.presentation.model

sealed interface CharacterInfoEvent {

    data object OnDataFetch: CharacterInfoEvent
    data object OnNavigateUp: CharacterInfoEvent
}