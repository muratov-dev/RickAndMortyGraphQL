package dev.ymuratov.feature.character_info.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.network.NetworkResult
import dev.ymuratov.feature.character_info.domain.repository.CharacterInfoRepository
import dev.ymuratov.navigation.Destination
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = CharacterInfoViewModel.Factory::class)
class CharacterInfoViewModel @AssistedInject constructor(
    @Assisted val navKey: Destination.CharacterInfoScreen,
    private val repository: CharacterInfoRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            val characterName = repository.fetchCharacterInfo(navKey.id)
            if (characterName is NetworkResult.Success){
                Log.d("CharacterInfoViewModel", characterName.data?.name.toString())
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: Destination.CharacterInfoScreen): CharacterInfoViewModel
    }
}