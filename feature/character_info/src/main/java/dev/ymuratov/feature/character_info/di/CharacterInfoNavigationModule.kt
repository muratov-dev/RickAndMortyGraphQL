package dev.ymuratov.feature.character_info.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import dev.ymuratov.feature.character_info.presentation.screen.CharacterInfoContainer
import dev.ymuratov.feature.character_info.presentation.viewmodel.CharacterInfoViewModel
import dev.ymuratov.navigation.Destination
import dev.ymuratov.navigation.EntryProviderInstaller
import dev.ymuratov.navigation.RaMNavigator

@Module
@InstallIn(ActivityRetainedComponent::class)
object CharacterInfoNavigationModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: RaMNavigator): EntryProviderInstaller = {
        entry<Destination.CharacterInfoScreen> { key ->
            val viewModel = hiltViewModel<CharacterInfoViewModel, CharacterInfoViewModel.Factory>(creationCallback = { factory ->
                factory.create(key)
            })
            CharacterInfoContainer(
                viewModel = viewModel,
                navigateUp = { navigator.goBack() },
                navigateToCharacter = { navigator.goTo(Destination.CharacterInfoScreen(it)) })
        }
    }
}