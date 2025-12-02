package dev.ymuratov.feature.character_info.domain.repository

import dev.ymuratov.core.network.NetworkResult
import dev.ymuratov.feature.character_info.domain.model.CharacterInfoModel

interface CharacterInfoRepository {

    suspend fun fetchCharacterInfo(characterId: String): NetworkResult<CharacterInfoModel?>
}