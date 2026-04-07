package io.github.nicogeissinger.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import io.github.nicogeissinger.core_ui.BackgroundDark
import io.github.nicogeissinger.core_ui.OnDark
import io.github.nicogeissinger.core_ui.OnPrimary
import io.github.nicogeissinger.core_ui.PrimaryOrange
import io.github.nicogeissinger.core_ui.SurfaceDark

private val RrlColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimary,
    onBackground = OnDark,
    onSurface = OnDark
)

@Composable
fun RRLCompanionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RrlColorScheme,
        content = content
    )
}