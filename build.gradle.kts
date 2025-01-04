plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    application
}

group = "io.tornado"
version = "1.0-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.xn32:json5k:0.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("io.netty:netty-all:4.1.116.Final")
}

application {
    mainClass.set("io.tornado.core.MainKt")
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_23
    }
}
