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

rootProject.name = "SamplePracticeWithoutSeeing"
include(":app")
include(":feature:detail:data")
include(":feature:detail:domain")
include(":feature:detail:ui")

include(":feature:movie:data")
include(":feature:movie:domain")
include(":feature:movie:ui")
include(":core:navigation")
include(":core:network")
include(":core:common")
include(":core:filedownloader")
