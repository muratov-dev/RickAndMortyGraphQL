package dev.ymuratov.ram_gql

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import dev.ymuratov.core.ui.theme.RaMTheme
import dev.ymuratov.navigation.EntryProviderInstaller
import dev.ymuratov.navigation.RaMNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: RaMNavigator

    @Inject
    lateinit var entryProviderScopes: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            RaMTheme {
                NavDisplay(
                    backStack = navigator.backStack, onBack = { navigator.goBack() }, entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(), rememberViewModelStoreNavEntryDecorator()
                    ), entryProvider = entryProvider {
                        entryProviderScopes.forEach { builder -> this.builder() }
                    })
            }
        }
    }
}