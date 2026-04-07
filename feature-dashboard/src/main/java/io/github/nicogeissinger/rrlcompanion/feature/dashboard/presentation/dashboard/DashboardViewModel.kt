package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nicogeissinger.rrlcompanion.feature.dashboard.domain.ObserveNextEventUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    observeNextEventUseCase: ObserveNextEventUseCase
) : ViewModel() {

    val uiState = observeNextEventUseCase()
        .map { event ->
            if (event == null) {
                DashboardUiState.NoEvent
            } else {
                Log.d("Info", "Participant count is ${event.participantCount}")

                DashboardUiState.EventAvailable(
                    classId = event.classId,
                    title = event.title,
                    trackname = event.trackName,
                    date = formatDate(event.startTimeUtcMillis),
                    startTime = formatTime(event.startTimeUtcMillis),
                    participantCount = event.participantCount
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardUiState.Loading
        )


    //TODO: Extract formatters to core-common when needed in other features
    private fun formatDate(utcMillis: Long): String {
        val instant = Instant.ofEpochMilli(utcMillis)
        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

    private fun formatTime(utcMillis: Long): String {
        val instant = Instant.ofEpochMilli(utcMillis)
        val date = instant.atZone(ZoneId.systemDefault()).toLocalTime()
        return date.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

}