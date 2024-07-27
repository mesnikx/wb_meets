plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.test {
    useJUnitPlatform()
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin.core)
    testImplementation (libs.koin.test.junit5)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation (libs.kotlinx.coroutines.test)
}