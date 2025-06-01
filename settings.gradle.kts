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

rootProject.name = "TaskApp"
include(":app")
include(":core:data")
include(":core:ui")
include(":core:network")
include(":core:designsystem")
include(":core:common")
include(":core:testing")
include(":core:datastore")
include(":core:database")
include(":core:model")
include(":feature:login")
include(":feature:home")
