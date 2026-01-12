package dev.ymuratov.feature.character_info.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.ymuratov.core.ui.utils.createImageLoader

@Composable
fun CharacterInfoImage(image: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = image,
        imageLoader = createImageLoader(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
            .aspectRatio(1f)
    )
}