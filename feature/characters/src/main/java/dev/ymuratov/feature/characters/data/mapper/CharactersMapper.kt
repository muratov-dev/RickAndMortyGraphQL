package dev.ymuratov.feature.characters.data.mapper

import dev.ymuratov.core.network.GetCharactersQuery
import dev.ymuratov.feature.characters.domain.model.CharacterModel

fun GetCharactersQuery.Result.toDomain(): CharacterModel {
    return CharacterModel(
        gender = gender ?: "",
        id = id ?: "",
        location = location?.name ?: "",
        name = name ?: "",
        species = species ?: "",
        type = type ?: "",
        status = status ?: "",
        image = image ?: ""
    )
}