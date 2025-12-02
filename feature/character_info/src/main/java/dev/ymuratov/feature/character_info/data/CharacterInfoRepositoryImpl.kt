package dev.ymuratov.feature.character_info.data

import com.apollographql.apollo.ApolloClient
import dev.ymuratov.core.network.GetCharacterInfoQuery
import dev.ymuratov.core.network.NetworkResult
import dev.ymuratov.core.network.map
import dev.ymuratov.core.network.safeQuery
import dev.ymuratov.feature.character_info.data.mapper.toDomain
import dev.ymuratov.feature.character_info.domain.model.CharacterInfoModel
import dev.ymuratov.feature.character_info.domain.repository.CharacterInfoRepository
import javax.inject.Inject

class CharacterInfoRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : CharacterInfoRepository {

    override suspend fun fetchCharacterInfo(characterId: String): NetworkResult<CharacterInfoModel?> {
        val characterInfo = safeQuery { apolloClient.query(GetCharacterInfoQuery(characterId)).execute() }
        return characterInfo.map { character ->
            character.character?.toDomain()
        }
    }
}