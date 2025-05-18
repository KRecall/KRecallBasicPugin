import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.plugin.compose") version ("2.1.10")
    id("org.jetbrains.compose") version ("1.7.0")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    id("org.jetbrains.kotlin.plugin.serialization") version("1.8.10")
}

group = "io.github.octestx.krecall.plugins.basiclib"
version = "1.4.9"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(libs.basic.multiplatform.lib)
                implementation(libs.basic.multiplatform.ui.lib)
                implementation(libs.kotlinx.serialization.json)
//                implementation("androidx.compose.runtime:runtime:1.5.4")
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.animation)
                implementation("org.ktorm:ktorm-core:4.1.1")
                implementation("org.ktorm:ktorm-support-sqlite:4.1.1")
                implementation("org.xerial:sqlite-jdbc:3.45.2.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "io.github.octestx.krecall.plugins.basiclib"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "KRecall Plugin Dev Lib"
        description = "KRecall Plugin Dev Lib"
        inceptionYear = "2025"
        url = "https://github.com/KRecall/KRecallBasicPugin/"
        licenses {
            license {
                name = "Apache-2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "OCTestX"
                name = "OCTestX"
                url = "https://github.com/OCTestX"
            }
        }
        scm {
            url = "https://github.com/KRecall/KRecallBasicPugin"
            connection = "scm:git:git://github.com/KRecall/KRecallBasicPugin.git"
            developerConnection = "scm:git:ssh://github.com/KRecall/KRecallBasicPugin.git"
        }
    }
}
