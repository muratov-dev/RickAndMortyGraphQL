package dev.ymuratov.feature.characters.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
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
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = RaMTheme.colors.iconButtonPrimaryDefault, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_top_right),
                    contentDescription = null,
                    tint = RaMTheme.colors.iconButtonContentPrimary
                )
            }
        }
        Spacer(Modifier.size(12.dp))
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(addInfo) {
                if (it.isNotEmpty()) {
                    TagText(text = it)
                }
            }
        }
    }
}

@Composable
fun TagText(text: String, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(100.dp)
    Box(
        modifier = modifier
            .border(width = 1.dp, color = RaMTheme.colors.borderColor, shape = shape)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text, style = RaMTheme.typography.bodyRegular, color = RaMTheme.colors.textPrimary
        )
    }
}