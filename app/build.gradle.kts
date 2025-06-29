plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinKsp)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "ml.vladmikh.projects.bincard"
    compileSdk = 35

    defaultConfig {
        applicationId = "ml.vladmikh.projects.bincard"
        minSdk = 26
        targetSdk = 34
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
        compose = true
    }
}

dependencies {

    //Navigation
    implementation(libs.androidx.navigation.compose )

    //Dagger - Hilt
    implementation (libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.firebase.crashlytics.buildtools)
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.compiler)

    // Retrofit
    implementation (libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata)

    //Room
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.annotation.processor)
    ksp(libs.androidx.room.annotation.processor)

    implementation(libs.androidx.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}