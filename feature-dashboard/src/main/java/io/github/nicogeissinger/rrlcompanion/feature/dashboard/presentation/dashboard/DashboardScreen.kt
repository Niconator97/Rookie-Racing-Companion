package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nächstes Event",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        when(uiState) {
            DashboardUiState.Loading -> {
                LoadingNextEventCard()
            }

            DashboardUiState.NoEvent -> {
                NoEventCard()
            }

            is DashboardUiState.EventAvailable -> {
                NextEventCard(
                    trackname = uiState.trackname,
                    classId = uiState.classId,
                    startTime = uiState.startTime,
                    date = uiState.date,
                    participantCount = uiState.participantCount
                )
            }
        }
    }
}

@Composable
private fun LoadingNextEventCard() {
    Column {
        CircularProgressIndicator()
    }
}

@Composable
private fun NoEventCard() {
    NextEventCard(
        classId = "",
        trackname = "Für kein Event registriert",
        date = "",
        startTime = "",
        participantCount = 0
    )
}