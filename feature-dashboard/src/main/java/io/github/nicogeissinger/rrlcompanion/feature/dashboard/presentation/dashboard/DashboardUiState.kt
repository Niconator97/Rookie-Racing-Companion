package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.dashboard

sealed interface DashboardUiState {

    object  Loading : DashboardUiState

    data class EventAvailable(
        val title: String,
        val classId: String,
        val trackname: String,
        val date: String,
        val startTime: String,
        val participantCount: Int
    ) : DashboardUiState

    object NoEvent : DashboardUiState
}