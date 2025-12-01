package dev.ymuratov.feature.characters.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.feature.characters.data.repository.CharactersRepositoryImpl
import dev.ymuratov.feature.characters.domain.repository.CharactersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository
}