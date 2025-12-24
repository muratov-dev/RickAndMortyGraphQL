package dev.ymuratov.core.ui.component.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.RaMTheme

object RaMTextFieldDefaults {

    private val HorizontalPadding = 16.dp
    private val VerticalPadding = 16.dp
    val ContentPadding = PaddingValues(HorizontalPadding, VerticalPadding)

    val MinHeight = 56.dp
    val Shape = RoundedCornerShape(100.dp)

    @Composable
    fun colors(): RaMTextFieldColors {
        val colors = RaMTheme.colors

        return colors.defaultTextFieldColorsCached ?: RaMTextFieldColors(
            defaultTextColor = colors.textPrimary,
            defaultContainerColor = colors.backgroundSecondary,
            defaultCursorColor = colors.textPrimary,
            defaultPlaceholderColor = colors.textSecondary,
        ).also {
            colors.defaultTextFieldColorsCached = it
        }
    }
}

data class RaMTextFieldColors(
    val defaultTextColor: Color,
    val defaultContainerColor: Color,
    val defaultCursorColor: Color,
    val defaultPlaceholderColor: Color,
)