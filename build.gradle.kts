// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.androidTools)
        classpath(Dependencies.kotlinPlugin)
        classpath(Dependencies.hiltPlugin)
        classpath(Dependencies.kotlinGradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
