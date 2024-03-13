plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.spread.xdplib"
  compileSdk = 34

  defaultConfig {
    minSdk = 29

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  api("org.jsoup:jsoup:$jsoupVersion")
  api("androidx.constraintlayout:constraintlayout:2.1.4")
  api("com.squareup.retrofit2:retrofit:2.9.0")
  api("com.squareup.retrofit2:converter-gson:2.9.0")
  api("com.alibaba:arouter-api:1.5.2")
  kapt("com.alibaba:arouter-compiler:1.5.2")

}