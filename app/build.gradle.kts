val ARCH_ABI by extra(project.properties["ARCH_ABI"] as? String ?: "arm64-v8a")
val STL_TYPE by extra(project.properties["STL_TYPE"] as? String ?: "c++_shared")
val SFML_STATIC by extra(project.properties["SFML_STATIC"] as? String ?: "OFF")

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
            abiFilters.add(ARCH_ABI)
        }
        externalNativeBuild {
            cmake {
                arguments.add("-DANDROID_STL=${STL_TYPE}")
                arguments.add("-DSFML_STATIC_LIBRARIES=${SFML_STATIC}")
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
