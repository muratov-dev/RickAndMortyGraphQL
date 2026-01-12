package dev.ymuratov.feature.character_info.presentation.viewmodel

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.network.NetworkResult
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.character_info.domain.repository.CharacterInfoRepository
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoAction
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoEvent
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoState
import dev.ymuratov.navigation.Destination

@HiltViewModel(assistedFactory = CharacterInfoViewModel.Factory::class)
class CharacterInfoViewModel @AssistedInject constructor(
    @Assisted val navKey: Destination.CharacterInfoScreen, private val repository: CharacterInfoRepository
) : BaseViewModel<CharacterInfoState, CharacterInfoEvent, CharacterInfoAction>(CharacterInfoState()) {

    override fun obtainEvent(viewEvent: CharacterInfoEvent) {
        when (viewEvent) {
            CharacterInfoEvent.OnDataFetch -> fetchData()
            CharacterInfoEvent.OnNavigateUp -> sendAction(CharacterInfoAction.NavigateUp)
            is CharacterInfoEvent.OnNavigateToCharacter -> sendAction(CharacterInfoAction.NavigateToCharacter(viewEvent.characterId))
        }
    }

    private fun fetchData() = viewModelScoped {
        updateViewState { copy(isLoading = true) }
        val result = repository.fetchCharacterInfo(navKey.id)
        if (result is NetworkResult.Success) {
            updateViewState { copy(isLoading = false, characterInfo = result.data) }
        } else {
            updateViewState { copy(isLoading = false) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: Destination.CharacterInfoScreen): CharacterInfoViewModel
    }
}