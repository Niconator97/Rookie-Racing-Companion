package io.github.nicogeissinger.core.database.seed

import android.util.Log
import androidx.room.withTransaction
import io.github.nicogeissinger.core.database.RrlDatabase
import io.github.nicogeissinger.core.database.dao.DriverDao
import io.github.nicogeissinger.core.database.dao.EventEntryDao
import io.github.nicogeissinger.core.database.dao.RaceClassDao
import io.github.nicogeissinger.core.database.dao.RaceEventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Add seeding for drivers and classes

@Singleton
class DatabaseSeeder @Inject constructor(
    private val db: RrlDatabase,
    private val driverDao: DriverDao,
    private val raceClassDao: RaceClassDao,
    private val raceEventDao: RaceEventDao,
    private val eventEntryDao: EventEntryDao,
    private val seedDataFactory: SeedDataFactory
) {
    suspend fun seedIfEmpty() = withContext(
        Dispatchers.IO) {
        val hasData = raceEventDao.countEvents() > 0
        if (hasData) return@withContext

        db.withTransaction {
            raceClassDao.upsertAll(seedDataFactory.createClasses())
            driverDao.upsertAll(seedDataFactory.createDrivers())
            raceEventDao.upsertAll(seedDataFactory.createEvents(Instant.now()))
            eventEntryDao.upsertAll(seedDataFactory.createEntries())
        }

        Log.d("Info", "Event Count ${raceEventDao.countEvents()}")
        Log.d("Info", "Entry Count ${eventEntryDao.observeParticipantCount("rtm_e1")}")

    }
}