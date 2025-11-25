package dev.ymuratov.core.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun Modifier.defaultModifier(): Modifier = fillMaxSize()
    .background(color = RaMTheme.colors.backgroundPrimary)