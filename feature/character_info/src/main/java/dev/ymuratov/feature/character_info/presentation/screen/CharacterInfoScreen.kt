package dev.ymuratov.feature.character_info.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ymuratov.feature.character_info.presentation.viewmodel.CharacterInfoViewModel

@Composable
fun CharacterInfoContainer(modifier: Modifier = Modifier, viewModel: CharacterInfoViewModel) {
    CharacterInfoContent(modifier = modifier, id = viewModel.navKey.id)
}

@Composable
private fun CharacterInfoContent(modifier: Modifier = Modifier, id: String) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Text(text = id)
        }
    }
}