
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)
        }
    }

}

