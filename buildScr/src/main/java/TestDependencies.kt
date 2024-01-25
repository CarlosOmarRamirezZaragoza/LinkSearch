import org.gradle.api.artifacts.dsl.DependencyHandler

object TestDependencies {
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitTest by lazy { "androidx.test.ext:junit:${Versions.jUnitTest}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val espressoCon by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espresso}" }
    val uiTest by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.uiTest}" }
    val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockitoCore}" }
    val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
    val mockitoInLine by lazy { "org.mockito:mockito-inline:${Versions.mockitoInLine}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}" }
    val hiltAndroidTesting by lazy { "com.google.dagger:hilt-android-testing:${Versions.hiltAndroidTesting}" }
    val hiltAndroidCompilerTesting by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompilerTesting}" }
    val robolectric by lazy { "org.robolectric:robolectric:${Versions.robolectric}" }
    val roomTest by lazy { "androidx.room:room-testing:${Versions.roomTest}" }
}

fun DependencyHandler.unitTesting() {
    testImplementation(TestDependencies.jUnit)
    testImplementation(TestDependencies.jUnitTest)
    testImplementation(TestDependencies.uiTest)
    testImplementation(TestDependencies.mockitoKotlin)
    testImplementation(TestDependencies.mockitoInLine)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.hiltAndroidTesting)
    testImplementation(TestDependencies.robolectric)
    kapt(TestDependencies.hiltAndroidCompilerTesting)
    kaptInstrumentation(TestDependencies.hiltAndroidCompilerTesting)
    androidTestImplementation(TestDependencies.espressoCon)
    androidTestImplementation(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.hiltAndroidTesting)
    androidTestImplementation(TestDependencies.jUnitTest)
}

fun DependencyHandler.androidTestImplementation(depName: Any) {
    add("androidTestImplementation", depName)
}

fun DependencyHandler.testImplementation(depName: Any) {
    add("testImplementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kaptTest", depName)
}

private fun DependencyHandler.kaptInstrumentation(depName: String) {
    add("kaptAndroidTest", depName)
}
