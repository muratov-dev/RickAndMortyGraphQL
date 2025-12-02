package dev.ymuratov.feature.character_info.data.mapper

import dev.ymuratov.core.network.GetCharacterInfoQuery
import dev.ymuratov.feature.character_info.domain.model.CharacterInfoModel

fun GetCharacterInfoQuery.Character.toDomain(): CharacterInfoModel {
    return CharacterInfoModel(
        id = id ?: "",
        name = name ?: ""
    )
}