plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "io.tornado"
version = "1.0-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
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
