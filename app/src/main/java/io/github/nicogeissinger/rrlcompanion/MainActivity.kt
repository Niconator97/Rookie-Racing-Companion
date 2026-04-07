package io.github.nicogeissinger.rrlcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.github.nicogeissinger.core.database.seed.DatabaseSeeder
import io.github.nicogeissinger.rrlcompanion.navigation.AppNavHost
import io.github.nicogeissinger.rrlcompanion.ui.theme.RRLCompanionTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   @Inject
   lateinit var databaseSeeder: DatabaseSeeder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isReady by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                databaseSeeder.seedIfEmpty()
                isReady = true
            }

            RRLCompanionTheme {
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    if (isReady) {
                        AppNavHost()
                    } else {
                        LoadingScreen()
                    }
                }
            }
        }
    }

    @Composable
    private fun LoadingScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}