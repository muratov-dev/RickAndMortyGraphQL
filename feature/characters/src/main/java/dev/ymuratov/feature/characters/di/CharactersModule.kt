package dev.ymuratov.feature.characters.di

import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import dev.ymuratov.core.ui.utils.defaultModifier
import dev.ymuratov.feature.characters.presentation.screen.CharactersContainer
import dev.ymuratov.feature.characters.presentation.viewmodel.CharactersViewModel
import dev.ymuratov.navigation.Destination
import dev.ymuratov.navigation.EntryProviderInstaller
import dev.ymuratov.navigation.RaMNavigator

@Module
@InstallIn(ActivityRetainedComponent::class)
object CharactersModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: RaMNavigator): EntryProviderInstaller = {
        entry<Destination.CharactersScreen> {
            val viewModel = hiltViewModel<CharactersViewModel>()
            CharactersContainer(
                modifier = Modifier.defaultModifier(),
                viewModel = viewModel,
                navigateToCharacterInfo = { navigator.goTo(Destination.CharacterInfoScreen(it)) })
        }
    }
}