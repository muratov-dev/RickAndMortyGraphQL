package dev.ymuratov.feature.character_info.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.utils.OnLifecycleEvent
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoAction
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoEvent
import dev.ymuratov.feature.character_info.presentation.model.CharacterInfoState
import dev.ymuratov.feature.character_info.presentation.viewmodel.CharacterInfoViewModel
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

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


    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.characterInfo?.let { info ->
                    SubcomposeAsyncImage(
                        model = info.image,
                        imageLoader = imageLoader,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                    Text(text = info.name)
                    Text(text = info.origin)
                    Text(text = "${info.species}|${info.type}")
                    Text(text = info.status)
                    Text(text = info.gender)

                    info.location?.let { location ->
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .background(color = Color.LightGray),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = location.name)
                            Text(text = location.type)
                            Text(text = location.dimension)
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(location.residents) {
                                    SubcomposeAsyncImage(
                                        model = it?.image,
                                        contentDescription = null,
                                        imageLoader = imageLoader,
                                        modifier = Modifier
                                            .size(56.dp)
                                            .background(Color.Gray)
                                    )
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .background(Color.LightGray),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        info.episode.forEach { episode ->
                            episode?.let {
                                Column {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = it.name)
                                        Text(text = it.episode)
                                    }
                                    Text(text = it.airDate)
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.size(innerPadding.calculateBottomPadding()))
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.White.copy(alpha = 0.2f))
                    .clickable { onEvent(CharacterInfoEvent.OnNavigateUp) }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}