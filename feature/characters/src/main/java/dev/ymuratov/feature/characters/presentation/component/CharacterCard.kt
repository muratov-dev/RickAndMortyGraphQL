package dev.ymuratov.feature.characters.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.ymuratov.core.ui.component.button.RaMIconButton
import dev.ymuratov.core.ui.component.text.RaMTagText
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.characters.R
import dev.ymuratov.feature.characters.domain.model.CharacterModel

@Composable
fun CharacterCard(character: CharacterModel, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val addInfo = listOf(character.gender, character.status, character.species, character.type)

    val colors = listOf(
        RaMTheme.colors.backgroundCard1,
        RaMTheme.colors.backgroundCard2,
        RaMTheme.colors.backgroundCard3,
        RaMTheme.colors.backgroundCard4,
    )
    val color = colors[character.id.toInt() % colors.size]
    Column(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(32.dp))
        .background(color = color)
        .clickable { onClick() }
        .padding(vertical = 16.dp)) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = character.name, style = RaMTheme.typography.titleLarge, color = RaMTheme.colors.textPrimary)
                Text(text = character.location, style = RaMTheme.typography.bodyRegular, color = RaMTheme.colors.textPrimary)
            }
            RaMIconButton(icon = R.drawable.ic_arrow_top_right)
        }
        Spacer(Modifier.size(12.dp))
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(addInfo) {
                if (it.isNotEmpty()) {
                    RaMTagText(text = it)
                }
            }
        }
    }
}