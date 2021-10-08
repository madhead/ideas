plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    application
}

dependencies {
    implementation(platform(libs.kotlin.bom))

    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.koin.core)
    implementation(libs.ktor.client.java)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.auth)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)

    testRuntimeOnly(libs.junit.jupiter.engine)
}

application {
    mainClass.set("me.madhead.ideas.AppKt")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            languageVersion = "1.6"
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
    }
}
