package dev.ymuratov.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import dev.ymuratov.core.ui.component.textfield.RaMTextFieldColors

@Immutable
data class RaMColors(
    val backgroundPrimary: Color = Color.Unspecified,
    val backgroundSecondary: Color = Color.Unspecified,
    val backgroundCard1: Color = Color.Unspecified,
    val backgroundCard2: Color = Color.Unspecified,
    val backgroundCard3: Color = Color.Unspecified,
    val backgroundCard4: Color = Color.Unspecified,
    val iconButtonPrimaryDefault: Color = Color.Unspecified,
    val iconButtonContentPrimary: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textAccent: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
) {
    var defaultTextFieldColorsCached: RaMTextFieldColors? = null
    companion object {
        @Stable
        val Light = RaMColors(
            backgroundPrimary = Color(0xFFEEEEF6),
            backgroundSecondary = Color(0xFFFFFFFF),
            backgroundCard1 = Color(0xFFE0F37D),
            backgroundCard2 = Color(0xFFA9EBF9),
            backgroundCard3 = Color(0xFF92E498),
            backgroundCard4 = Color(0xFFDAB8F4),
            iconButtonPrimaryDefault = Color(0xFF1E1E1E),
            iconButtonContentPrimary = Color(0xFFFFFFFF),
            textPrimary = Color(0xFF000000),
            textSecondary = Color(0xFF282828),
            textAccent = Color(0xFF38BC42),
            borderColor = Color(0xFF000000),
        )
    }
}

internal val LocalRaMColors = staticCompositionLocalOf { RaMColors() }