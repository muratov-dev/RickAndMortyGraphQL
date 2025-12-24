package dev.ymuratov.core.ui.component.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline

@Composable
fun RaMTextFieldContainer(
    modifier: Modifier = Modifier,
    shape: Shape = RaMTextFieldDefaults.Shape,
    colors: RaMTextFieldColors = RaMTextFieldDefaults.colors(),
) {
    Box(
        modifier = modifier.drawWithCache {
            val outline = shape.createOutline(size, layoutDirection, this)
            onDrawBehind { drawOutline(outline, colors.defaultContainerColor) }
        })
}