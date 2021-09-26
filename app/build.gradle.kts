plugins {
    kotlin("jvm").version("1.5.0")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

application {
    mainClass.set("me.madhead.ideas.AppKt")
}
