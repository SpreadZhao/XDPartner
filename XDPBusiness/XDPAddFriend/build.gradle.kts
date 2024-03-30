plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.spread.xdpbusiness.xdpaddfriend"
    compileSdk = 34

    defaultConfig {
        minSdk = 29
        consumerProguardFiles("consumer-rules.pro")
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    api(project(":XDPLib"))
    api(project(":XDPNetwork"))
    kapt("com.alibaba:arouter-compiler:1.5.2")
}