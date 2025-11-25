package dev.ymuratov.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class RaMColors(
    val backgroundPrimary: Color = Color.Unspecified,
    val backgroundSecondary: Color = Color.Unspecified,
    val buttonPrimaryDefault: Color = Color.Unspecified,
    val buttonTextPrimary: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
) {

    companion object {
        @Stable
        val Dark = RaMColors(
            backgroundPrimary = Color(0xFF1E1B29),
            backgroundSecondary = Color(0xFF29243B),
            buttonPrimaryDefault = Color(0xFF32FF9D),
            buttonTextPrimary = Color(0xFFFFFFFF),
            textPrimary = Color(0xFFFFFFFF),
            textSecondary = Color(0xFFB0B0B0),
        )
    }
}

internal val LocalRaMColors = staticCompositionLocalOf { RaMColors() }