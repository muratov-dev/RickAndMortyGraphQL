package dev.ymuratov.feature.character_info.presentation.model

import androidx.compose.runtime.Immutable
import dev.ymuratov.feature.character_info.domain.model.CharacterInfoModel

@Immutable
data class CharacterInfoState(
    val isLoading: Boolean = true,
    val characterInfo: CharacterInfoModel? = null
)
