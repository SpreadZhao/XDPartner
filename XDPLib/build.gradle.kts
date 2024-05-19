plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("kotlin-parcelize")
}

android {
  namespace = "com.spread.xdplib"
  compileSdk = 34

  defaultConfig {
    minSdk = 29
    consumerProguardFiles("consumer-rules.pro")
    javaCompileOptions {
      annotationProcessorOptions {
        arguments(mapOf("AROUTER_MODULE_NAME" to project.name))
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
val jsoupVersion = rootProject.extra["jsoupVersion"]
dependencies {
  api("androidx.core:core-ktx:1.12.0")
  api("androidx.appcompat:appcompat:1.6.1")
  api("com.google.android.material:material:1.11.0")
  api("org.jsoup:jsoup:$jsoupVersion")
  api("androidx.constraintlayout:constraintlayout:2.1.4")
  api("com.squareup.retrofit2:retrofit:2.9.0")
  api("com.squareup.retrofit2:converter-gson:2.9.0")
  api(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
  api("com.squareup.okhttp3:okhttp")
  api("com.squareup.okhttp3:logging-interceptor")
  implementation(files("libs/CodeLocatorCore-debug.aar", "libs/CodeLocatorModel.jar"))
  api("com.alibaba:arouter-api:1.5.2")
  kapt("com.alibaba:arouter-compiler:1.5.2")
  api("com.tencent:mmkv:1.3.4")
  api("com.github.bumptech.glide:glide:4.16.0") // 替换成你使用的Glide版本
  kapt("com.github.bumptech.glide:compiler:4.16.0") // 注解处理器
  api("io.ktor:ktor-client-android:1.6.4")
  api("io.ktor:ktor-client-websockets:1.6.4")
  api("io.ktor:ktor-client-okhttp:1.6.4")
}