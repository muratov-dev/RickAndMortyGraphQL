package dev.ymuratov.feature.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.apollographql.apollo.ApolloClient
import dev.ymuratov.feature.characters.data.CharactersPagingSource
import dev.ymuratov.feature.characters.data.mapper.toDomain
import dev.ymuratov.feature.characters.domain.model.CharacterModel
import dev.ymuratov.feature.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : CharactersRepository {

    override suspend fun fetchCharacters(searchQuery: String): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharactersPagingSource(apolloClient, searchQuery) }).flow.map { data ->
            data.map { dto -> dto.toDomain() }
        }
    }
}