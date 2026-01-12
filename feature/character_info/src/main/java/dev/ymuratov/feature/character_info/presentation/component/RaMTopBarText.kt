package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun RowScope.RaMTopBarText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = RaMTheme.typography.titleLarge,
        color = RaMTheme.colors.textPrimary,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = modifier.weight(1f)
    )
}