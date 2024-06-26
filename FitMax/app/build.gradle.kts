plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.fitmax"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fitmax"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions{
            annotationProcessorOptions {
//                arguments = ["room.schemaLocation":"$projectDir/schemas".toString()]
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // chart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // progress bar
    implementation("com.mikhaellopez:circularprogressbar:3.1.0")


    // room ----------------------------------------------------------------------------------------
    val room_version = "2.6.1"
    val lifecycle_version = "2.7.0"
    val arch_version = "2.1.0"

    // room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

//    implementation("com
//    .android.support.constraint:constraint-layout:2.0.4")
//    androidTestImplementation("android.arch.core:core-testing:$room_version")

        // lifecycle
//    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
//    annotationProcessor("androidx.core:core-testing:$arch_version")



//    // To use Kotlin annotation processing tool (kapt)
//    kapt("androidx.room:room-compiler:$room_version")
//    // To use Kotlin Symbol Processing (KSP)
//    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")
    // ---------------------------------------------------------------------------------------------
}