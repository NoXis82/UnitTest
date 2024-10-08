plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "com.noxis.unittest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.noxis.unittest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "${project.properties["API_KEY"]}")

        testInstrumentationRunner = "com.noxis.unittest.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    tasks.withType<Test> { useJUnitPlatform() }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("androidx.fragment:fragment-ktx:1.8.2")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation ("androidx.test:core-ktx:1.6.0")

    // Material Design
    implementation ("com.google.android.material:material:1.3.0-alpha02")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.room.compiler)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)


    testImplementation(libs.jupiter.api)
    testImplementation(libs.jupiter.params)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testRuntimeOnly(libs.jupiter.engine)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.core.testing)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.46")

    androidTestImplementation(libs.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.truth)
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.3.0")

//    Mockito
    androidTestImplementation(libs.mockito)
    testImplementation(libs.mockito)

    debugImplementation("androidx.fragment:fragment-testing-manifest:1.8.2")


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    debugImplementation(libs.fragment.testing)
}