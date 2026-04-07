plugins {
    id("rrl.android.library")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.github.nicogeissinger.rrlcompanion.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
}