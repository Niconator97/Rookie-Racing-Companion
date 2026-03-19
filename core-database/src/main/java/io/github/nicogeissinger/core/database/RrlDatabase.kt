package io.github.nicogeissinger.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.nicogeissinger.core.database.dao.EventEntryDao
import io.github.nicogeissinger.core.database.dao.RaceEventDao
import io.github.nicogeissinger.core.database.entity.DriverClassCrossRefEntity
import io.github.nicogeissinger.core.database.entity.DriverEntity
import io.github.nicogeissinger.core.database.entity.DriverStandingsEntity
import io.github.nicogeissinger.core.database.entity.EventEntryEntity
import io.github.nicogeissinger.core.database.entity.RaceClassEntity
import io.github.nicogeissinger.core.database.entity.RaceEventEntity

@Database(
    entities = [
        RaceClassEntity::class,
        DriverEntity::class,
        DriverClassCrossRefEntity::class,
        RaceEventEntity::class,
        EventEntryEntity::class,
        DriverStandingsEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RrlDatabase: RoomDatabase() {
    abstract fun raceEventDao(): RaceEventDao
    abstract fun eventEntryDao(): EventEntryDao
}