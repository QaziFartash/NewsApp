plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.plugin.compose)
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 25  // You might want to lower this based on your app's compatibility
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary=true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"  // Make sure the JVM target is set to 11 for Kotlin
    }

    buildFeatures {
        compose= true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"  // Ensure the latest compiler extension for Jetpack Compose
    }
//    allprojects {
//        repositories {
//            google()
//            mavenCentral()
//            maven("https://jitpack.io") // Required for JitPack libraries
//        }
//    }



    // new files add

}


dependencies {

    // Core dependencies
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.compose.material)

    // Kotlin and Compose BOMs
    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    val kotlinBom = platform("org.jetbrains.kotlin:kotlin-bom:1.9.24")

    implementation(kotlinBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Jetpack Compose UI dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")

    // Debug dependencies for Compose UI
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")


    // Dagger Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Room Database dependencies
    val roomVersion = "2.6.1" // Use a single version variable for Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //Coroutines Lifecycle scope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //Retrofit For Api calls
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0") // Latest stable Glide version
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    //Newsapi dependency
    implementation("com.github.KwabenBerko:News-API-Java:1.0.2")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil:2.4.0")




    // Horizontal Pager
//    implementation ("com.google.accompanist:accompanist-pager:0.35.0") // Latest stable version
//    implementation ("com.google.accompanist:accompanist-pager-indicators:0.35.0")

}