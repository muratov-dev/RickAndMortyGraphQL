package dev.ymuratov.feature.characters.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.characters.domain.model.CharacterModel

@Composable
fun CharacterCard(character: CharacterModel, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val cardShape = RoundedCornerShape(12.dp)
    val addInfo = listOf(character.gender, character.status, character.species)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = RaMTheme.colors.backgroundSecondary, shape = cardShape)
            .clip(cardShape)
            .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "name")
                Text(text = character.name)
                Text(text = "last known location")
                Text(text = character.location)
                Spacer(Modifier.weight(1f))
                LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(addInfo) {
                        Text(text = it, modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
}