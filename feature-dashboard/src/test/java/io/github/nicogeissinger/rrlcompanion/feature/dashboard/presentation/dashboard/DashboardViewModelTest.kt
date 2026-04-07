package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.dashboard

import io.github.nicogeissinger.core.data.model.RaceEvent
import io.github.nicogeissinger.core.data.repository.RaceEventRepository
import io.github.nicogeissinger.rrlcompanion.feature.dashboard.domain.ObserveNextEventUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState is EventAvailable when useCase emits event`() = runTest {
        val event = RaceEvent(
            eventId = "event_1",
            classId = "RTM",
            title = "GT3 Sprint",
            trackName = "Zandvoort",
            startTimeUtcMillis = 1_700_000_000_000,
            endTimeUtcMillis = 1_700_000_900_000,
            participantCount = 12
        )

        val repository = FakeRaceEventRepository(flowOf(event))
        val useCase = ObserveNextEventUseCase(repository)

        val viewModel = DashboardViewModel(useCase)

        val state = viewModel.uiState.first { it !is DashboardUiState.Loading }

        assertTrue(state is DashboardUiState.EventAvailable)
    }

    @Test
    fun `uiState is NoEvent when useCase emits null`() = runTest {
        val repository = FakeRaceEventRepository(flowOf(null))
        val useCase = ObserveNextEventUseCase(repository)

        val viewModel = DashboardViewModel(useCase)

        val state = viewModel.uiState.first { it !is DashboardUiState.Loading }

        assertTrue(state is DashboardUiState.NoEvent)
    }

    @Test
    fun `uiState is Loading on start`() = runTest {
        val repository = FakeRaceEventRepository(emptyFlow())
        val useCase = ObserveNextEventUseCase(repository)

        val viewModel = DashboardViewModel(useCase)

        assertTrue(viewModel.uiState.value is DashboardUiState.Loading)
    }

    @Test
    fun `uiState maps event to EventAvailable correctly`() = runTest {
        val event = RaceEvent(
            eventId = "event_1",
            classId = "RTM",
            title = "GT3 Sprint",
            trackName = "Zandvoort",
            startTimeUtcMillis = 1_700_000_000_000,
            endTimeUtcMillis = 1_700_000_900_000,
            participantCount = 12
        )

        val repository = FakeRaceEventRepository(flowOf(event))
        val useCase = ObserveNextEventUseCase(repository)

        val viewModel = DashboardViewModel(useCase)
        val state = viewModel.uiState.first { it is DashboardUiState.EventAvailable }

        assertTrue(state is DashboardUiState.EventAvailable)

        state as DashboardUiState.EventAvailable

        assertEquals("RTM", state.classId)
        assertEquals("GT3_Sprint", state.title)
        assertEquals("Zandvoort", state.trackname)
        assertEquals(12, state.participantCount)
    }
}

private class FakeRaceEventRepository(
    private val flow: Flow<RaceEvent?>
) : RaceEventRepository {
    override fun observeNextEvent(): Flow<RaceEvent?> = flow
}