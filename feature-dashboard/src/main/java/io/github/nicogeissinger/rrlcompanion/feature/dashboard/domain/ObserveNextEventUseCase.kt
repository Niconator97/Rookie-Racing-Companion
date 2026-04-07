package io.github.nicogeissinger.rrlcompanion.feature.dashboard.domain

import io.github.nicogeissinger.core.data.model.RaceEvent
import io.github.nicogeissinger.core.data.repository.RaceEventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveNextEventUseCase @Inject constructor(
    private val repository: RaceEventRepository
) {
    operator fun invoke(): Flow<RaceEvent?> {
        return repository.observeNextEvent()
    }
}