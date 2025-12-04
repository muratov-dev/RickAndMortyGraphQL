package dev.ymuratov.feature.characters.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.characters.domain.repository.CharactersRepository
import dev.ymuratov.feature.characters.presentation.model.CharactersAction
import dev.ymuratov.feature.characters.presentation.model.CharactersEvent
import dev.ymuratov.feature.characters.presentation.model.CharactersState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseViewModel<CharactersState, CharactersEvent, CharactersAction>(CharactersState()) {

    private val _searchQueryFlow = MutableStateFlow("")
    private val searchQueryFlow: StateFlow<String> = _searchQueryFlow.asStateFlow()

    override fun obtainEvent(viewEvent: CharactersEvent) {
        when (viewEvent) {
            is CharactersEvent.OnCharacterCardClick -> sendAction(CharactersAction.NavigateToCharactersInfo(viewEvent.id))
            is CharactersEvent.OnSearchQueryChanged -> {
                _searchQueryFlow.value = viewEvent.name
                updateViewState { copy(searchQuery = viewEvent.name) }
            }
        }
    }

    init {
        refreshData()
    }

    private fun refreshData() = viewModelScoped {
        val charactersFlow = searchQueryFlow.debounce(300).distinctUntilChanged().flatMapLatest { query ->
            charactersRepository.fetchCharacters(query)
        }.cachedIn(viewModelScope)
            .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000L), initialValue = PagingData.empty())

        updateViewState { copy(charactersFlow = charactersFlow) }
    }
}