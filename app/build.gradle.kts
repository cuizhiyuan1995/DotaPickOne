


plugins {
    id ("com.android.application")
    id ("com.apollographql.apollo3")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
//    id ("androidx.navigation.safeargs")
    id ("com.google.android.gms.oss-licenses-plugin")
}




android {

    val versionMajor = 95
    val versionMinor = 4
    val versionPatch = 0

    compileSdk = 34

    defaultConfig {
        applicationId = "ai.pipi.dotapickone"
        minSdk = 26
        targetSdk = 34
        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue ("string", "app_version", "${versionName}")
        signingConfig = signingConfigs.getByName("debug")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    namespace = "ai.pipi.dotapickone"
    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.4.0"
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.appcompat:appcompat:1.6.0")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3-android:1.3.2")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    //splash screen
    implementation ("androidx.core:core-splashscreen:1.0.0")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.0-alpha01")

    //graphql
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.2")
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.2")

    //okhttp
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")


    //room

    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.5.0")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.5.0")


    //multik
    implementation("org.jetbrains.kotlinx:multik-core:0.2.0")
    //implementation("org.jetbrains.kotlinx:multik-default:0.2.0")
    implementation("org.jetbrains.kotlinx:multik-kotlin:0.2.0")

    //balloon
    implementation("com.github.skydoves:balloon:1.2.9")
    implementation("com.github.skydoves:balloon-compose:1.5.3")

    //jetpack compose
    implementation ("androidx.compose.foundation:foundation:1.3.1")
    implementation ("androidx.compose.material:material:1.4.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.3")

    //open source license
    implementation ("com.google.android.gms:play-services-oss-licenses:17.0.1")

}

apollo {
    packageName.set("ai.pipi.dotapickone")
}