plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.xdpartner"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    defaultConfig {
        applicationId = "com.example.xdpartner"
        minSdk = 29
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}



dependencies {
    api(project(":XDPBusiness:XDPMe"))
    api(project(":XDPBusiness:XDPLogin"))
    api(project(":XDPBusiness:XDPSearch"))
    api(project(":XDPBusiness:XDPFriendList"))
    api(project(":XDPBusiness:XDPAddNote"))
    api(project(":XDPBusiness:XDPMessage"))
    api(project(":XDPBusiness:XDPAddFriend"))
    api(project(":XDPBusiness:XDPPersonDetail"))
    api(project(":XDPNetwork"))
    api("com.alibaba:arouter-api:1.5.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt("com.alibaba:arouter-compiler:1.5.2")
    api("io.ktor:ktor-client-android:1.6.4")
    api("io.ktor:ktor-client-websockets:1.6.4")
    api("io.ktor:ktor-client-okhttp:1.6.4")
}