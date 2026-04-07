package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.nicogeissinger.core_ui.PrimaryOrange

@Composable
fun NextEventCard(
    classId: String,
    trackname: String,
    date: String,
    startTime: String,
    participantCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        PrimaryOrange,
                        Color(0xFFFFA040)
                    ),
                    start = Offset(500f, 500f)
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "$date - $startTime",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = trackname,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$participantCount Teilnehmer",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}