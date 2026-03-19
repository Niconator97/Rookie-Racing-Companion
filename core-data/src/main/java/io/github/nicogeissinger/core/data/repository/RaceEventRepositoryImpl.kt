package io.github.nicogeissinger.core.data.repository

import io.github.nicogeissinger.core.data.mapper.toRaceEvent
import io.github.nicogeissinger.core.data.model.RaceEvent
import io.github.nicogeissinger.core.database.dao.RaceEventDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RaceEventRepositoryImpl @Inject constructor(
    private val raceEventDao: RaceEventDao
) : RaceEventRepository {

    override fun observeNextEvent(): Flow<RaceEvent?> {
        return raceEventDao.observeNextEventWithParticipantCount(
            nowUtcMillis = System.currentTimeMillis()
        )
            .map { result ->
                result?.toRaceEvent()
            }
    }
}