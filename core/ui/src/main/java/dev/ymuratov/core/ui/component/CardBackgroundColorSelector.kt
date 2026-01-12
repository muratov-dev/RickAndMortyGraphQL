package dev.ymuratov.core.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun characterCardColor(characterId: Int): Color {
    val colors = listOf(
        RaMTheme.colors.backgroundCard1,
        RaMTheme.colors.backgroundCard2,
        RaMTheme.colors.backgroundCard3,
        RaMTheme.colors.backgroundCard4,
    )
    val color = colors[characterId % colors.size]
    return color
}