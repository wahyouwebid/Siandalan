import buildtype.AndroidBuildType
import config.AndroidConfig
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        namespace = AndroidConfig.APP_NAME_SPACE
        minSdk = AndroidConfig.MIN_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENT_RUNNER
    }

    buildTypes {
        named(AndroidBuildType.DEBUG) {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"http://116.0.4.24:808/siandalan-pengembangan/\"")
            buildConfigField("String", "webUrl", "\"http://116.0.4.24:808/siandalan2022/public/\"")
        }

        named(AndroidBuildType.RELEASE) {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"http://116.0.4.24:808/siandalan-pengembangan/\"")
            buildConfigField("String", "webUrl", "\"http://116.0.4.24:808/siandalan2022/public/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    //UI
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(Dependencies.ANDROIDX_ACTIVITY_KTX)
    implementation(Dependencies.ANDROIDX_FRAGENT_KTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.MPACHART)
    implementation(Dependencies.SHIMMER)
    implementation(Dependencies.EXPANDABLE)

    // Navigation Component
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)

    //Networking
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_JAVA)
    implementation(Dependencies.CONVERTER_GSON)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_INTERCEPTOR)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    debugImplementation(Dependencies.CHUCKER)
    releaseImplementation(Dependencies.CHUCKER_NOOP)

    //Dependency Injection
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_ANDROIDX_COMPILER)

    // RxJava
    implementation(Dependencies.RX_JAVA)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.RX_BINDING)

    //Security
    implementation(Dependencies.SECURITY)

    //Firebase
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_ANALYTIC)
    implementation(Dependencies.FIREBASE_MESSAGING)

    //Testing
    testImplementation(Dependencies.JUNIT)
    testImplementation(Dependencies.MOCKITO)
    androidTestImplementation(Dependencies.ANDROIDX_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)

}