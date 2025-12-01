package dev.ymuratov.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dev.ymuratov.navigation.Destination
import dev.ymuratov.navigation.RaMNavigator

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavigationModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNavigator(): RaMNavigator = RaMNavigator(startDestination = Destination.CharactersScreen)
}