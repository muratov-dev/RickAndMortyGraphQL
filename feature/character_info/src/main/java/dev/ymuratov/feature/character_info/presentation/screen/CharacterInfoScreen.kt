package dev.ymuratov.feature.character_info.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.core.ui.utils.OnLifecycleEvent
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoAction
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoEvent
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoState
import dev.ymuratov.feature.character_info.presentation.viewmodel.CharacterInfoViewModel
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.math.ceil

@Composable
fun CharacterInfoContainer(modifier: Modifier = Modifier, viewModel: CharacterInfoViewModel, navigateUp: () -> Unit = {}) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle(viewModel) { action ->
        when (action) {
            CharacterInfoAction.NavigateUp -> navigateUp()
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
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).okHttpClient {
        OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }.crossfade(true).build()
    var heroHeightPx by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val collapseProgress by remember {
        derivedStateOf {
            if (heroHeightPx == 0) return@derivedStateOf 0f
            (scrollState.value / heroHeightPx.toFloat()).coerceIn(0f, 1f)
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
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(40.dp)
                    .background(color = RaMTheme.colors.iconButtonPrimaryDefault, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = RaMTheme.colors.iconButtonContentPrimary
                )
            }

            Text(
                text = state.characterInfo?.name ?: "",
                style = RaMTheme.typography.titleLarge,
                color = RaMTheme.colors.textPrimary,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .alpha(collapseProgress)
            )

            Spacer(Modifier.size(40.dp))
        }
    }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.characterInfo?.let { info ->
                    val colors = listOf(
                        RaMTheme.colors.backgroundCard1,
                        RaMTheme.colors.backgroundCard2,
                        RaMTheme.colors.backgroundCard3,
                        RaMTheme.colors.backgroundCard4,
                    )
                    val color = colors[info.id.toInt() % colors.size]
                    val addInfo = listOf(info.gender, info.status, info.species, info.type)
                    SubcomposeAsyncImage(
                        model = info.image,
                        imageLoader = imageLoader,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
                            .aspectRatio(1f)
                            .onGloballyPositioned { coordinates ->
                                heroHeightPx = coordinates.size.height
                            })

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .background(color = RaMTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp))
                            .padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = info.name,
                            style = RaMTheme.typography.titleLarge,
                            color = RaMTheme.colors.textPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(dev.ymuratov.feature.character_info.R.drawable.ic_location),
                                contentDescription = null,
                                tint = RaMTheme.colors.textPrimary
                            )
                            Text(text = info.origin, style = RaMTheme.typography.bodyBold, color = RaMTheme.colors.textPrimary)
                        }
                        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp)) {
                            items(addInfo) {
                                if (it.isNotEmpty()) {
                                    TagText(text = it)
                                }
                            }
                        }
                    }

                    info.location?.let { location ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .background(color = color, shape = RoundedCornerShape(24.dp))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Last Location", style = RaMTheme.typography.labelBold, color = RaMTheme.colors.textPrimary
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(
                                text = location.name, style = RaMTheme.typography.titleLarge, color = RaMTheme.colors.textPrimary
                            )
                            Spacer(Modifier.size(8.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                TagText(text = location.type)
                                TagText(text = location.dimension)
                            }
                            Spacer(Modifier.size(16.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.White.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Residents",
                                    style = RaMTheme.typography.labelBold,
                                    color = RaMTheme.colors.textPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Spacer(Modifier.size(12.dp))
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp)
                                ) {
                                    items(location.residents) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(84.dp)
                                        ) {
                                            SubcomposeAsyncImage(
                                                model = it?.image,
                                                contentDescription = null,
                                                imageLoader = imageLoader,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .aspectRatio(1f)
                                                    .clip(shape = RoundedCornerShape(16.dp))
                                                    .background(Color.Gray)
                                            )
                                            Spacer(Modifier.size(8.dp))
                                            Text(
                                                text = it?.name ?: "",
                                                style = RaMTheme.typography.labelMedium,
                                                color = RaMTheme.colors.textPrimary,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .background(color = RaMTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp))
                            .padding(16.dp)
                    ) {
                        val rows = ceil(info.episode.size / 2f).toInt()
                        val gridHeight = rows * 64.dp
                        Text(
                            text = "Episodes",
                            style = RaMTheme.typography.labelBold,
                            color = RaMTheme.colors.textPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(Modifier.size(12.dp))
                        LazyVerticalGrid(
                            modifier = Modifier.heightIn(max = gridHeight * 2),
                            columns = GridCells.Fixed(2),
                            userScrollEnabled = false,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(info.episode) { episode ->
                                episode?.let {
                                    Column(
                                        modifier = Modifier
                                            .background(
                                                color = RaMTheme.colors.backgroundPrimary, shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(8.dp)
                                    ) {
                                        Row {
                                            Text(
                                                text = it.name,
                                                style = RaMTheme.typography.textSemiBold,
                                                color = RaMTheme.colors.textPrimary,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Spacer(Modifier.size(4.dp))
                                            Text(
                                                text = it.episode,
                                                style = RaMTheme.typography.labelBold,
                                                color = Color(0xFF38BC42),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier
                                                    .wrapContentWidth()
                                                    .background(color = Color(0xFFDFDFEF), shape = RoundedCornerShape(100.dp))
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                        Spacer(Modifier.size(4.dp))
                                        Text(
                                            text = it.airDate,
                                            style = RaMTheme.typography.labelBold,
                                            color = RaMTheme.colors.textPrimary
                                        )
                                    }
                                }
                            }
                        }
//                        info.episode.forEach { episode ->
//                            episode?.let {
//                                Column {
//                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                                        Text(text = it.name)
//                                        Text(text = it.episode)
//                                    }
//
//                                }
//                            }
//                        }
                    }
                }
                Spacer(Modifier.size(innerPadding.calculateBottomPadding()))
            }
        }
    }
}

@Composable
fun TagText(text: String, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(100.dp)
    Box(
        modifier = modifier
            .border(width = 1.dp, color = RaMTheme.colors.borderColor, shape = shape)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text, style = RaMTheme.typography.bodyRegular, color = RaMTheme.colors.textPrimary
        )
    }
}