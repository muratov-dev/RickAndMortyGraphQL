package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.character_info.domain.model.EpisodeModel

@Composable
fun EpisodeCard(episode: EpisodeModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = RaMTheme.colors.backgroundPrimary, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Row {
            Text(
                text = episode.name,
                style = RaMTheme.typography.textSemiBold,
                color = RaMTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = episode.episode,
                style = RaMTheme.typography.labelBold,
                color = RaMTheme.colors.textAccent,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth()
                    .background(color = Color(0xFFDFDFEF), shape = RoundedCornerShape(100.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Spacer(Modifier.size(4.dp))
        Text(text = episode.airDate, style = RaMTheme.typography.labelBold, color = RaMTheme.colors.textPrimary)
    }
}