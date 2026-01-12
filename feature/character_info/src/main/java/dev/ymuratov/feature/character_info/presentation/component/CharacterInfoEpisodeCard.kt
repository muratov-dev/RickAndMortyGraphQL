package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.character_info.domain.model.EpisodeModel
import kotlin.math.ceil

@Composable
fun CharacterInfoEpisodeCard(episodes: List<EpisodeModel?>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .background(color = RaMTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        val rows = ceil(episodes.size / 2f).toInt()
        val gridHeight = rows * 64.dp
        Text(
            text = stringResource(dev.ymuratov.feature.character_info.R.string.character_info_episode_title),
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
            items(episodes) {
                it?.let { episode ->
                    EpisodeCard(episode)
                }
            }
        }
    }
}