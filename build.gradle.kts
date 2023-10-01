import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.1"
}

group = "nsu.shelbogashev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
//    implementation("ru.nsu.shelbogashev:snake-io-core:1.0-SNAPSHOT")
    implementation(files("../snake-io-core/build/out/snake-io-core-1.0-SNAPSHOT-all.jar"))
}

kotlin {
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_1_9)
        jvmTarget.set(JvmTarget.JVM_17)
        jvmToolchain(17)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "snake-io"
            packageVersion = "1.0.0"
        }
    }
}

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/EricShelbogashev/snake-io-core")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}