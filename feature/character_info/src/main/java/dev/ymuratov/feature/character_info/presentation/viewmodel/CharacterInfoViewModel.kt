package dev.ymuratov.feature.character_info.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.navigation.Destination
import javax.inject.Inject

@HiltViewModel(assistedFactory = CharacterInfoViewModel.Factory::class)
class CharacterInfoViewModel @AssistedInject constructor(
    @Assisted val navKey: Destination.CharacterInfoScreen
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(navKey: Destination.CharacterInfoScreen): CharacterInfoViewModel
    }
}