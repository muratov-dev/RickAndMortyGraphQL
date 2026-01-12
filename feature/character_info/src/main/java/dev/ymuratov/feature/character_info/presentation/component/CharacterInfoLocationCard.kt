package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.component.text.RaMTagText
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.character_info.R
import dev.ymuratov.feature.character_info.domain.model.LocationModel

@Composable
fun CharacterInfoLocationCard(location: LocationModel, modifier: Modifier = Modifier, onResidentClick: (String) -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.character_info_last_location_title),
            style = RaMTheme.typography.labelBold,
            color = RaMTheme.colors.textPrimary
        )
        Spacer(Modifier.size(8.dp))
        Text(text = location.name, style = RaMTheme.typography.titleLarge, color = RaMTheme.colors.textPrimary)
        Spacer(Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            RaMTagText(text = location.type)
            RaMTagText(text = location.dimension)
        }
        Spacer(Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.character_info_residents_title),
                style = RaMTheme.typography.labelBold,
                color = RaMTheme.colors.textPrimary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(Modifier.size(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
                items(location.residents) {
                    it?.let { resident ->
                        LocationResidentCard(image = resident.image, name = resident.name) {
                            onResidentClick(resident.id)
                        }
                    }
                }
            }
        }
    }
}