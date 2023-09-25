plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.nike.products"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.nike.products"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }




    ndkVersion = "23.1.7779620"

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }


    buildFeatures {
        buildConfig = true
    }
    flavorDimensions ("full")
    productFlavors {

        create("product")  {
            dimension ="full"
            //buildConfigField 'String', 'BASE_URL', '"https://ddindia.co/rest/apiv1/"'
            buildConfigField( "int", "APP_CONFIG", "1")
        }
        create("stage") {
            dimension= "full"
            //  buildConfigField 'String', 'BASE_URL', '"http://ddindia.php-staging.com/rest/apiv1/"'
            buildConfigField("int", "APP_CONFIG", "2")
            //buildConfigField 'String', 'BASE_URL', '"https://ddindia.co/rest/apiv1/"'

        }
        create("local") {
            dimension ="full"
            buildConfigField( "int", "APP_CONFIG", "3")
            //   buildConfigField 'String', 'BASE_URL', '"http://ddindia.php-staging.com/rest/apiv1/"'
        }
    }



    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix=".debug"

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    //livedata
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.46.1")

    //shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    //image slider
    implementation("me.relex:circleindicator:2.1.6")

    //splash core
    implementation("androidx.core:core-splashscreen:1.0.1")

    //reactive
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //room
    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-rxjava2:2.5.2")

    //retrofit for API Call
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    implementation ("com.squareup.picasso:picasso:2.8")


}