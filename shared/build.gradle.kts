plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    // ANDROID
    android()

    // IOS
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Shared code for the Hello World example"
        homepage = "https://github.com/pablichjenkov/templato"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        version = "1.0.0"
        framework {
            baseName = "HelloWorldKt"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    // JS
    js(IR) {
        browser()
    }

    // JVM
    jvm("desktop")

    sourceSets {
        // COMMON
        val commonMain by getting {
            dependencies {
                api("io.github.pablichjenkov:component-toolkit:0.4.1")
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        // ANDROID
        val androidMain by getting
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val androidInstrumentedTest by getting

        // IOS
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        // JS
        val jsMain by getting

        // JVM
        val desktopMain by getting
    }

}

android {
    namespace = "com.pablichj.incubator.uistate3.example.helloWorld"
    compileSdk = 33
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}