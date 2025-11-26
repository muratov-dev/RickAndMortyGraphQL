package dev.ymuratov.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRaMDatabase(@ApplicationContext context: Context): RaMDatabase =
        Room.databaseBuilder(context, RaMDatabase::class.java, "RaM.db").build()
}