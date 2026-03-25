plugins {
    id("rrl.android.library")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "io.github.nicogeissinger.rrlcompanion.feature.dashboard"
    buildFeatures { compose = true }
}

dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)

    implementation(project(":core-common"))
    implementation(project(":core-ui"))
    implementation(project(":core-data"))
    testImplementation(project(":core-testing"))
}