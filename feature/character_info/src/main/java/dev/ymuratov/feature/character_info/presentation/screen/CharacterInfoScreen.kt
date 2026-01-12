package dev.ymuratov.feature.character_info.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.component.button.RaMIconButton
import dev.ymuratov.core.ui.component.characterCardColor
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.core.ui.utils.OnLifecycleEvent
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.character_info.presentation.component.CharacterInfoEpisodeCard
import dev.ymuratov.feature.character_info.presentation.component.CharacterInfoImage
import dev.ymuratov.feature.character_info.presentation.component.CharacterInfoLocationCard
import dev.ymuratov.feature.character_info.presentation.component.CharacterInfoMainCard
import dev.ymuratov.feature.character_info.presentation.component.RaMTopBarText
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoAction
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoEvent
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoState
import dev.ymuratov.feature.character_info.presentation.viewmodel.CharacterInfoViewModel

@Composable
fun CharacterInfoContainer(
    modifier: Modifier = Modifier,
    viewModel: CharacterInfoViewModel,
    navigateUp: () -> Unit = {},
    navigateToCharacter: (String) -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle(viewModel) { action ->
        when (action) {
            CharacterInfoAction.NavigateUp -> navigateUp()
            is CharacterInfoAction.NavigateToCharacter -> navigateToCharacter(action.characterId)
            null -> {}
        }
    }
    OnLifecycleEvent(Lifecycle.Event.ON_RESUME) {
        viewModel.obtainEvent(CharacterInfoEvent.OnDataFetch)
    }
    CharacterInfoContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
private fun CharacterInfoContent(
    modifier: Modifier = Modifier, state: CharacterInfoState = CharacterInfoState(), onEvent: (CharacterInfoEvent) -> Unit = {}
) {
    val screenScrollState = rememberScrollState()
    var characterImageHeightPx by remember { mutableIntStateOf(0) }
    val collapseProgress by remember {
        derivedStateOf {
            if (characterImageHeightPx == 0) return@derivedStateOf 0f
            (screenScrollState.value / characterImageHeightPx.toFloat()).coerceIn(0f, 1f)
        }
    }

    Scaffold(modifier = modifier, topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(color = RaMTheme.colors.backgroundSecondary.copy(alpha = collapseProgress))
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RaMIconButton(icon = R.drawable.ic_arrow_back, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                onEvent(CharacterInfoEvent.OnNavigateUp)
            }
            RaMTopBarText(text = state.characterInfo?.name ?: "", modifier = Modifier.alpha(collapseProgress))
            Spacer(Modifier.size(40.dp))
        }
    }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(screenScrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.characterInfo?.let { info ->
                    CharacterInfoImage(image = info.image, modifier = Modifier.onGloballyPositioned { coordinates ->
                        characterImageHeightPx = coordinates.size.height
                    })
                    CharacterInfoMainCard(
                        name = info.name, origin = info.origin, traits = listOf(info.gender, info.status, info.species, info.type)
                    )
                    info.location?.let { location ->
                        CharacterInfoLocationCard(
                            location = location,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .background(color = characterCardColor(info.id.toInt()), shape = RoundedCornerShape(24.dp)),
                            onResidentClick = {
                                if (it != info.id){
                                    onEvent(CharacterInfoEvent.OnNavigateToCharacter(it))
                                }
                            })
                    }
                    CharacterInfoEpisodeCard(info.episode)
                }
                Spacer(Modifier.size(innerPadding.calculateBottomPadding()))
            }
        }
    }
}