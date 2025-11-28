package dev.ymuratov.feature.characters.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.characters.domain.repository.CharactersRepository
import dev.ymuratov.feature.characters.presentation.model.CharactersState
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseViewModel<CharactersState, Any, Any>(CharactersState()) {

    override fun obtainEvent(viewEvent: Any) {
        TODO("Not yet implemented")
    }

    init {
        viewModelScoped {
            val flow = charactersRepository.fetchCharacters("")
            updateViewState { copy(charactersFlow = flow) }
        }
    }
}