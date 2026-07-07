plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.aks.parkingapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aks.parkingapp"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    // ---------- CORE ----------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // ---------- COMPOSE ----------
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.animation)

    debugImplementation(libs.androidx.ui.tooling)

    // ---------- FOUNDATION ONBOARDING ----------
    implementation(libs.androidx.compose.foundation)

    // ---------- NAVIGATION ----------
    implementation(libs.androidx.navigation.compose)

    // ---------- LIFECYCLE ----------
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // ---------- HILT ----------
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ---------- ROOM ----------
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ---------- NETWORK ----------
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // ---------- SYSTEM UI ----------
    implementation(libs.accompanist.systemuicontroller)

    // ---------- IMAGE ----------
    implementation(libs.coil.compose)

    // ---------- ICONS ----------
    implementation(libs.androidx.compose.material.icons.extended)

    // ---------- Secure CRYPTO ----------
    implementation(libs.androidx.security.crypto)

    // ---------- FCM --------------------
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)

    // ---------- FCM Analytics ----------
    implementation(libs.firebase.analytics)

    // ---------- LEAK CANARY ----------
    debugImplementation(
        libs.leakcanary.android
    )

    // ---------- WORK MANAGER ---------
    implementation(libs.androidx.work.runtime.ktx)

    // ---------- UNIT TEST ----------
    testImplementation(libs.junit)

    // ---------- ANDROID TEST (IMPORTANT) ----------
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.navigation.testing)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.room.testing)

    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}