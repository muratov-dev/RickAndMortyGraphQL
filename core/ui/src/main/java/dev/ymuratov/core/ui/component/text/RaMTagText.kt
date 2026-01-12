package dev.ymuratov.core.ui.component.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun RaMTagText(text: String, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(100.dp)
    Box(
        modifier = modifier
            .border(width = 1.dp, color = RaMTheme.colors.borderColor, shape = shape)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, style = RaMTheme.typography.bodyRegular, color = RaMTheme.colors.textPrimary)
    }
}