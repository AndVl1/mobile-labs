plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "ru.bmstu"
version "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.0")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "ru.bmstu.android"
        minSdk = 24
        targetSdk= 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}