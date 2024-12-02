plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.test.logger)
}

group = "eu.tinylinden"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(libs.strikt.core)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)

    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("-Xshare:off")
    }
}
