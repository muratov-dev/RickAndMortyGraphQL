package dev.ymuratov.ram_gql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.feature.characters.presentation.screen.CharactersContainer

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaMTheme {
                CharactersContainer()
            }
        }
    }
}