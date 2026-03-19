pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RRL Companion"
include(":app")
include(":core-common")
includeBuild("build-logic")
include(":core-testing")
include(":core-ui")
include(":core-database")
include(":feature-dashboard")
include(":feature-calendar")
include(":feature-standings")
include(":core-data")
