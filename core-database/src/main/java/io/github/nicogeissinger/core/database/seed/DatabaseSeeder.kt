package io.github.nicogeissinger.core.database.seed

import io.github.nicogeissinger.core.database.dao.EventEntryDao
import io.github.nicogeissinger.core.database.dao.RaceEventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Add seeding for drivers and classes

@Singleton
class DatabaseSeeder @Inject constructor(
    private val raceEventDao: RaceEventDao,
    private val eventEntryDao: EventEntryDao,
    private val seedDataFactory: SeedDataFactory
) {
    suspend fun seedIfEmpty() = withContext(
        Dispatchers.IO) {
        val hasData = raceEventDao.countEvents() > 0
        if (hasData) return@withContext

        raceEventDao.upsertAll(seedDataFactory.createEvents(Instant.now()))
        eventEntryDao.upsertAll(seedDataFactory.createEntries())
    }
}