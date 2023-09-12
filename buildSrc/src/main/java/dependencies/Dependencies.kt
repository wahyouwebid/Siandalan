package dependencies

import versions.Versions

object Dependencies {

    /** Android Jetpack **/
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_ACTIVITY_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val ANDROIDX_FRAGENT_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"

    /** Networking Retfotit & OkHttp **/
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_RX_JAVA = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_INTERCEPTOR}"
    const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"
    const val CHUCKER_NOOP = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"

    /** Dagger Hilt **/
    const val HILT = "com.google.dagger:hilt-android:${Versions.DAGGER}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.DAGGER}"
    const val HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT}"

    /** UI Library **/
    const val MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val MPACHART = "com.github.PhilJay:MPAndroidChart:${Versions.MPACHART}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER}"
    const val EXPANDABLE = "com.github.cachapa:ExpandableLayout:${Versions.EXPANDABLE}"
    const val AUTO_SLIDER = "com.github.dangiashish:Auto-Image-Slider:${Versions.AUTO_SLIDER}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    /** RxJava **/
    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${Versions.RX_ANDROID}"
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}"
    const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:${Versions.RX_BINDING}"
    /** Security **/
    const val SECURITY = "androidx.security:security-crypto:${Versions.SECURITY}"


    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
    const val FIREBASE_ANALYTIC = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging-ktx"

    /** Testing **/
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ANDROID_TEST_ESPRESSO}"
    const val JUNIT = "junit:junit:${Versions.TEST_JUNIT}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO}"
}