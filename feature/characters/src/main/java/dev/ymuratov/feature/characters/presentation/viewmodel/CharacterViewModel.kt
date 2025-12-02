package dev.ymuratov.feature.characters.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.characters.domain.repository.CharactersRepository
import dev.ymuratov.feature.characters.presentation.model.CharactersEvent
import dev.ymuratov.feature.characters.presentation.model.CharactersState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseViewModel<CharactersState, CharactersEvent, Any>(CharactersState()) {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery = _searchQuery.asStateFlow()

    override fun obtainEvent(viewEvent: CharactersEvent) {
        when (viewEvent) {
            is CharactersEvent.OnCharacterClick -> TODO()
            is CharactersEvent.OnSearchValueUpdate -> {
                _searchQuery.value = viewEvent.name
                updateViewState { copy(searchQuery = viewEvent.name) }
            }
        }
    }

    init {
        viewModelScoped {
            val flow =searchQuery.debounce(300).distinctUntilChanged().flatMapLatest { query ->
                charactersRepository.fetchCharacters(query)
            }.cachedIn(viewModelScope)
            updateViewState { copy(charactersFlow = flow) }
        }
    }
}