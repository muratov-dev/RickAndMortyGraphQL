package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.component.text.RaMTagText
import dev.ymuratov.core.ui.theme.RaMTheme

@Composable
fun CharacterInfoMainCard(
    name: String,
    origin: String,
    traits: List<String>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(color = RaMTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp))
            .padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            style = RaMTheme.typography.titleLarge,
            color = RaMTheme.colors.textPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(dev.ymuratov.feature.character_info.R.drawable.ic_location),
                contentDescription = null,
                tint = RaMTheme.colors.textPrimary
            )
            Text(text = origin, style = RaMTheme.typography.bodyBold, color = RaMTheme.colors.textPrimary)
        }
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(traits) {
                if (it.isNotEmpty()) {
                    RaMTagText(text = it)
                }
            }
        }
    }
}