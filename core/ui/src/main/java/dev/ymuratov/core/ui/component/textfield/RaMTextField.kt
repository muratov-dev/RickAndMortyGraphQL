package dev.ymuratov.core.ui.component.textfield

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun RaMTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = RaMTheme.typography.textMedium,
    placeholder: @Composable (BoxScope.() -> Unit)? = null,
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RaMTextFieldDefaults.Shape,
    colors: RaMTextFieldColors = RaMTextFieldDefaults.colors(),
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = colors.defaultTextColor))

    BoxWithConstraints(modifier = modifier, propagateMinConstraints = true) {
        Column(modifier = Modifier.height(IntrinsicSize.Min)) {
            BasicTextField(
                modifier = Modifier.widthIn(min = this@BoxWithConstraints.minWidth),
                value = value,
                onValueChange = onValueChange,
                textStyle = mergedTextStyle,
                singleLine = true,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(colors.defaultCursorColor),
                decorationBox = @Composable { innerTextField ->
                    RaMTextFieldDecorationBox(
                        value = value,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        leadingContent = leadingContent,
                        trailingContent = trailingContent,
                        shape = shape,
                        colors = colors
                    )
                })
        }
    }
}