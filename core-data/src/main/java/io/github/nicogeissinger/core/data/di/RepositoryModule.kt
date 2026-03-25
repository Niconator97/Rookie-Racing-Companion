package io.github.nicogeissinger.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.nicogeissinger.core.data.repository.RaceEventRepository
import io.github.nicogeissinger.core.data.repository.RaceEventRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRaceEventRepository(
        impl: RaceEventRepositoryImpl
    ): RaceEventRepository
}