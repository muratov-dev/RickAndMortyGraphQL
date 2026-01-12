package dev.ymuratov.core.ui.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun RaMTextFieldDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable (BoxScope.() -> Unit)? = null,
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null,
    shape: Shape = RaMTextFieldDefaults.Shape,
    colors: RaMTextFieldColors = RaMTextFieldDefaults.colors(),
    contentPadding: PaddingValues = RaMTextFieldDefaults.ContentPadding,
    container: @Composable BoxScope.() -> Unit = {
        RaMTextFieldContainer(shape = shape, colors = colors)
    }
) {
    val placeholderTextStyle = RaMTheme.typography.textMedium.merge(color = colors.defaultPlaceholderColor)

    Box(modifier = Modifier.defaultMinSize(minHeight = RaMTextFieldDefaults.MinHeight), propagateMinConstraints = true) {
        Box(modifier = Modifier.matchParentSize(), propagateMinConstraints = true) {
            container()
        }
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leadingContent?.let {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    leadingContent()
                }
            }

            Box(modifier = Modifier.weight(1f, true), contentAlignment = Alignment.CenterStart) {
                Box {
                    if (placeholder != null && value.isEmpty()) {
                        CompositionLocalProvider(LocalTextStyle provides placeholderTextStyle) {
                            placeholder()
                        }
                    }
                    innerTextField()
                }
            }

            trailingContent?.let {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    trailingContent()
                }
            }
        }
    }
}