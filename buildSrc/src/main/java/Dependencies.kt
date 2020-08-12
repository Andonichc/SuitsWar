object Versions {
    // Build config
    const val minSDK = 21
    const val targetSDK = 27
    const val compileSDK = 28
    const val buildTools = "29.0.3"

    const val kotlin = "1.3.72"
}

object LibrariesVersions {
    const val appCompat = "1.2.0"
    const val androidAnnotation = "1.1.0"
    const val dagger = "2.28.1"
    const val javaxAnnotation = "1"
    const val coroutines = "1.3.8"
    const val material = "1.1.0"
    const val constraintLayout = "1.1.3"
}

object PluginVersions {
    const val gradle = "4.1.0-beta05"
    const val gradleDependencies = "0.20.0"
}

object TestingVersions {
    const val jUnit = "4.12"
    const val mockito = "3.4.6"
    const val supportTest = "1.0.2"
    const val truth = "1.0.1"
}

object AppDeps {
    const val appCompat = "androidx.appcompat:appcompat:${LibrariesVersions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${LibrariesVersions.constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${LibrariesVersions.material}"
    const val androidAnnotations =
        "androidx.annotation:androidx.annotation:${LibrariesVersions.androidAnnotation}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${LibrariesVersions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${LibrariesVersions.dagger}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibrariesVersions.coroutines}"
}

object Deps {
    const val javaxAnnotation = "javax.inject:javax.inject:${LibrariesVersions.javaxAnnotation}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibrariesVersions.coroutines}"
    const val coroutinesCommon =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${LibrariesVersions.coroutines}"
    const val coroutinesReactive =
        "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${LibrariesVersions.coroutines}"
    const val dagger = "com.google.dagger:dagger:${LibrariesVersions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${LibrariesVersions.dagger}"
    const val daggerProcessor =
        "com.google.dagger:dagger-android-processor:${LibrariesVersions.dagger}"
}

object PluginDeps {
    const val gradlePlugin = "com.android.tools.build:gradle:${PluginVersions.gradle}"
    const val gradleDepsPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${PluginVersions.gradleDependencies}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object TestDeps {
    const val androidTestMock = "android.test.mock"
    const val junit = "junit:junit:${TestingVersions.jUnit}"
    const val junitExt = "androidx.test.ext:junit:${TestingVersions.jUnit}"
    const val mockito = "org.mockito:mockito-core:${TestingVersions.mockito}"
    const val truth = "com.google.truth:truth:${TestingVersions.truth}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${LibrariesVersions.coroutines}"
}
