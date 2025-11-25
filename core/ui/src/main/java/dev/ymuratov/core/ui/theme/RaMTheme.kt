package dev.ymuratov.core.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun RaMTheme(content: @Composable () -> Unit) {
    val colors = RaMColors.Dark
    val typography = RaMTypography()
    val defaultTextStyle = typography.textRegular

    CompositionLocalProvider(
        LocalRaMColors provides colors,
        LocalRaMTypography provides typography,
        LocalTextStyle provides defaultTextStyle,
    ) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                onSurface = RaMTheme.colors.textPrimary,
                surface = RaMTheme.colors.backgroundSecondary,
                background = RaMTheme.colors.backgroundPrimary,
                primary = RaMTheme.colors.buttonPrimaryDefault,
                onPrimary = RaMTheme.colors.buttonTextPrimary,
            ), typography = Typography(), content = content
        )
    }
}

object RaMTheme {
    val colors: RaMColors
        @Composable get() = LocalRaMColors.current

    val typography: RaMTypography
        @Composable get() = LocalRaMTypography.current
}

