@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.com.android.application)
}

android {
    namespace = "org.sfmldev.android"
    ndkVersion = libs.versions.ndk.get()
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "org.sfmldev.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
