import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val kotlinJdk by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinJdk}" }
    val kotlinGradle by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradle}" }
    val androidTools by lazy { "com.android.tools.build:gradle:${Versions.androidTools}" }
    val kotlinPlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}" }
    val room by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomCoroutines by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hiltCompiler}" }
    val hiltPlugin by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltPlugin}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltCompiler}" }
    val navigation by lazy { "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}" }
    val navigationFragmentKtx by lazy {
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit2}" }
    val moshi by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofit2}" }
    val expressoResource by lazy { "androidx.test.espresso:espresso-idling-resource:${Versions.expressoResource}" }
}

fun DependencyHandler.room() {
    implementation(Dependencies.room)
    implementation(Dependencies.roomCoroutines)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit2() {
    implementation(Dependencies.retrofit2)
    implementation(Dependencies.moshi)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUi)
}

fun DependencyHandler.app() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.kotlinJdk)
    implementation(Dependencies.expressoResource)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}
