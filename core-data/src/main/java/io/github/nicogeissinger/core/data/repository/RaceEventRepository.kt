package io.github.nicogeissinger.core.data.repository

import io.github.nicogeissinger.core.data.model.RaceEvent
import kotlinx.coroutines.flow.Flow

interface RaceEventRepository {
    fun observeNextEvent(): Flow<RaceEvent?>
}