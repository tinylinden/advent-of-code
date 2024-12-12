plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.test.logger)


    id("me.champeau.jmh") version "0.7.2"
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
    testImplementation(libs.jmh.generator.annprocess)

    jmh ("org.openjdk.jmh:jmh-core:1.36")
    jmh ("org.openjdk.jmh:jmh-generator-annprocess:1.36")

    // this is the line that solves the missing /META-INF/BenchmarkList error
    jmhAnnotationProcessor ("org.openjdk.jmh:jmh-generator-annprocess:1.36")


    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("-Xshare:off")
    }
}
