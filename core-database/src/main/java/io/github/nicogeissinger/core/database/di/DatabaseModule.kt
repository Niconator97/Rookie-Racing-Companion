package io.github.nicogeissinger.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.nicogeissinger.core.database.RrlDatabase
import io.github.nicogeissinger.core.database.dao.EventEntryDao
import io.github.nicogeissinger.core.database.dao.RaceEventDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "rrl.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RrlDatabase =
        Room.databaseBuilder(
            context,
            RrlDatabase::class.java,
            DB_NAME
        )
            // Für V1 ok. Später: echte Migrationen.
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRaceEventDao(db: RrlDatabase): RaceEventDao = db.raceEventDao()

    @Provides
    fun provideEventEntryDao(db: RrlDatabase): EventEntryDao = db.eventEntryDao()
}