package dev.ymuratov.feature.character_info.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.feature.character_info.data.CharacterInfoRepositoryImpl
import dev.ymuratov.feature.character_info.domain.repository.CharacterInfoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterInfoRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterInfoRepository(impl: CharacterInfoRepositoryImpl): CharacterInfoRepository
}