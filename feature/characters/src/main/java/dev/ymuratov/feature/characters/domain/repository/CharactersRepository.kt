package dev.ymuratov.feature.characters.domain.repository

import androidx.paging.PagingData
import dev.ymuratov.feature.characters.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun fetchCharacters(searchQuery: String): Flow<PagingData<CharacterModel>>
}