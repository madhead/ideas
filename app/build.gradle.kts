plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

dependencies {
    implementation(platform(libs.kotlin.bom))

    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.koin.core)
    implementation(libs.ktor.client.java)
    implementation(libs.ktor.client.serialization)
}

application {
    mainClass.set("me.madhead.ideas.AppKt")
}
