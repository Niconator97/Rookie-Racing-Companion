package io.github.nicogeissinger.rrlcompanion.feature.dashboard.domain

import io.github.nicogeissinger.core.data.model.RaceEvent
import io.github.nicogeissinger.core.data.repository.RaceEventRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveNextEventUseCaseTest {

    private lateinit var repository: FakeRaceEventRepository
    private lateinit var useCase: ObserveNextEventUseCase

    @Before
    fun setup() {
        repository = FakeRaceEventRepository()
        useCase = ObserveNextEventUseCase(repository)
    }

    @Test
    fun `invoke emits next event from repository`() = runTest {
        val event = RaceEvent(
            eventId = "event_1",
            classId = "RTM",
            title = "GT3 Sprint Test",
            trackName = "Spa",
            startTimeUtcMillis = 1_700_000_000_000,
            endTimeUtcMillis = 1_700_000_900_000,
            participantCount = 12
        )

        repository.emit(event)

        val result = useCase().first()

        assertEquals(event, result)
    }

    @Test
    fun `invoke emits null when repository has no next event`() = runTest {
        repository.emit(null)

        val result = useCase().first()

        assertEquals(null, result)
    }
}

private class FakeRaceEventRepository : RaceEventRepository {

    private val flow = MutableSharedFlow<RaceEvent?>(replay = 1)

    suspend fun emit(event: RaceEvent?) {
        flow.emit(event)
    }

    override fun observeNextEvent(): Flow<RaceEvent?> = flow
}