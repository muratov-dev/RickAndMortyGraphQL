package dev.ymuratov.feature.character_info.data.mapper

import dev.ymuratov.core.network.GetCharacterInfoQuery
import dev.ymuratov.feature.character_info.domain.model.CharacterInfoModel
import dev.ymuratov.feature.character_info.domain.model.EpisodeModel
import dev.ymuratov.feature.character_info.domain.model.LocationModel
import dev.ymuratov.feature.character_info.domain.model.ResidentModel

fun GetCharacterInfoQuery.Character.toDomain(): CharacterInfoModel {
    return CharacterInfoModel(
        episode = episode.map { it?.toDomain() },
        gender = gender ?: "",
        id = id ?: "",
        image = image ?: "",
        location = location?.toDomain(),
        name = name ?: "",
        origin = origin?.name ?: "",
        species = species ?: "",
        status = status ?: "",
        type = type ?: "",
    )
}

private fun GetCharacterInfoQuery.Episode.toDomain(): EpisodeModel {
    return EpisodeModel(
        airDate = air_date ?: "",
        episode = episode ?: "",
        name = name ?: ""
    )
}

private fun GetCharacterInfoQuery.Location.toDomain(): LocationModel {
    return LocationModel(
        dimension = dimension ?: "",
        name = name ?: "",
        residents = residents.map { it?.toDomain() },
        type = type ?: ""
    )
}

private fun GetCharacterInfoQuery.Resident.toDomain(): ResidentModel {
    return ResidentModel(
        id = id ?: "",
        image = image ?: "",
        name = name ?: ""
    )
}