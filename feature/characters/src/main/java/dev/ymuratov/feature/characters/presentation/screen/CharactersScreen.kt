package dev.ymuratov.feature.characters.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.component.textfield.RaMTextField
import dev.ymuratov.core.ui.theme.RaMTheme
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

@OptIn(ExperimentalTextApi::class)
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    top = 16.dp, bottom = innerPadding.calculateBottomPadding(), start = 16.dp, end = 16.dp
                )
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        val text = AnnotatedString.Builder().apply {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black, fontFamily = FontFamily(
                                        Font(
                                            R.font.nunitosans_variable,
                                            variationSettings = FontVariation.Settings(FontVariation.weight(400))
                                        )
                                    )
                                )
                            ) {
                                append("Wubba Lubba ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green, fontFamily = FontFamily(
                                        Font(
                                            R.font.nunitosans_variable,
                                            variationSettings = FontVariation.Settings(FontVariation.weight(800))
                                        )
                                    )
                                )
                            ) {
                                append("Dub Dub!")
                            }
                        }.toAnnotatedString()
                        Text(text = text, fontSize = 24.sp)
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(dev.ymuratov.feature.characters.R.drawable.im_alien),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
                stickyHeader {
                    Box {
                        Spacer(
                            modifier = Modifier
                                .height(28.dp)
                                .fillMaxWidth()
                                .background(color = RaMTheme.colors.backgroundPrimary)
                        )
                        RaMTextField(
                            value = state.searchQuery,
                            onValueChange = { query -> onEvent(CharactersEvent.OnSearchQueryChanged(query)) },
                            placeholder = { Text(text = "Search...") },
                            leadingContent = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = RaMTheme.colors.textPrimary
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
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