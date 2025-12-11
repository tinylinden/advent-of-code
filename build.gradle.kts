import me.champeau.jmh.JMHTask

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.test.logger)
    alias(libs.plugins.jmh)
}

group = "eu.tinylinden"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.jgrapht.core)
    implementation(libs.jgrapht.io)
    implementation(libs.streamex)
    implementation(libs.jts.core)

    testImplementation(libs.strikt.core)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.jmh.generator.annprocess)

    testRuntimeOnly(libs.junit.jupiter.engine)

    jmh(libs.jmh.core)
    jmh(libs.jmh.generator.annprocess)
    jmhAnnotationProcessor(libs.jmh.generator.annprocess)
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("-Xshare:off")
    }

    withType<JMHTask> {
        project.properties["jmh.include"]?.let {
            includes = listOf(it.toString())
        }
    }
}
