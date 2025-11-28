package dev.ymuratov.feature.characters.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.ymuratov.feature.characters.presentation.model.CharactersState
import dev.ymuratov.feature.characters.presentation.viewmodel.CharacterViewModel

@Composable
fun CharactersContainer(modifier: Modifier = Modifier, viewModel: CharacterViewModel = hiltViewModel()) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    CharactersContent(modifier = modifier, state = state)
}

@Composable
private fun CharactersContent(modifier: Modifier = Modifier, state: CharactersState = CharactersState()) {
    val characters = state.charactersFlow.collectAsLazyPagingItems()
    Scaffold(modifier = modifier) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters.itemCount) { index ->
                val item = characters[index] ?: return@items
                Text(
                    text = item.name,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            if (characters.loadState.append is LoadState.Loading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}