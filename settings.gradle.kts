enableFeaturePreview(org.gradle.api.internal.FeaturePreviews.Feature.VERSION_CATALOGS.name)
enableFeaturePreview(org.gradle.api.internal.FeaturePreviews.Feature.TYPESAFE_PROJECT_ACCESSORS.name)

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "ideas"

include(":app")
