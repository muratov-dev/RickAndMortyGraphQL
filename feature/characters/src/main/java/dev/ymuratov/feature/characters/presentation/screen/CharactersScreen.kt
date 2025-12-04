package dev.ymuratov.feature.characters.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.characters.presentation.component.CharacterCard
import dev.ymuratov.feature.characters.presentation.model.CharactersAction
import dev.ymuratov.feature.characters.presentation.model.CharactersEvent
import dev.ymuratov.feature.characters.presentation.model.CharactersState
import dev.ymuratov.feature.characters.presentation.viewmodel.CharactersViewModel

@Composable
fun CharactersContainer(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToCharacterInfo: (characterId: String) -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle(viewModel) { action ->
        when (action) {
            is CharactersAction.NavigateToCharactersInfo -> navigateToCharacterInfo(action.id)
            null -> {}
        }
    }

    CharactersContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
private fun CharactersContent(
    modifier: Modifier = Modifier,
    state: CharactersState = CharactersState(),
    onEvent: (CharactersEvent) -> Unit = {},
) {
    val characters = state.charactersFlow.collectAsLazyPagingItems()
    Scaffold(modifier = modifier) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = characters.loadState.refresh is LoadState.Loading,
            onRefresh = { characters.refresh() },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
            ) {
                TextField(
                    value = state.searchQuery,
                    onValueChange = { query -> onEvent(CharactersEvent.OnSearchQueryChanged(query)) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        top = 16.dp, bottom = innerPadding.calculateBottomPadding(), start = 16.dp, end = 16.dp
                    )
                ) {
                    items(characters.itemCount) { index ->
                        val character = characters[index] ?: return@items
                        CharacterCard(character = character) {
                            onEvent(CharactersEvent.OnCharacterCardClick(character.id))
                        }
                    }

                    if (characters.loadState.append is LoadState.Loading) {
                        item { CircularProgressIndicator() }
                    }
                }
            }
        }
    }
}