package dev.ymuratov.feature.characters.presentation.model

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import dev.ymuratov.feature.characters.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class CharactersState(
    val charactersFlow: Flow<PagingData<CharacterModel>> = emptyFlow()
)
