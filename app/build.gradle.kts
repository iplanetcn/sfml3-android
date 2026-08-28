@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.application)
}

android {
    namespace = "org.sfmldev.android"
    compileSdk {
        version = release(36)
    }
    ndkVersion = "29.0.14206865"
    defaultConfig {
        applicationId = "org.sfmldev.android"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters.add("arm64-v8a")
        }

        externalNativeBuild {
            cmake {
                arguments.add("-DANDROID_STL=c++_shared")
                arguments.add("-DSFML_STATIC_LIBRARIES=OFF")
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
