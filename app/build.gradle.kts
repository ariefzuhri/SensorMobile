plugins {
    
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.dagger.hilt.android")
    
}

android {
    
    namespace = "sv.ugm.sensormobile"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "sv.ugm.sensormobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.2.1"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            
            versionNameSuffix = "-dev"
        }
        
        create("alpha") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
            versionNameSuffix = "-alpha"
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        
        isCoreLibraryDesugaringEnabled = true
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    buildFeatures {
        buildConfig = true
        compose = true
    }
    
    composeOptions {
        val composeCompilerVersion: String by rootProject.extra
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    
    secrets {
        propertiesFileName = "secrets.properties"
    }
    
}

dependencies {
    
    val activityVersion: String by rootProject.extra
    val coreVersion: String by rootProject.extra
    val lifecycleVersion: String by rootProject.extra
    val desugarJdkLibsVersion: String by rootProject.extra
    val composeVersion: String by rootProject.extra
    val navigationVersion: String by rootProject.extra
    val hiltVersion: String by rootProject.extra
    val hiltNavigationComposeVersion: String by rootProject.extra
    val roomVersion: String by rootProject.extra
    val dataStoreVersion: String by rootProject.extra
    val retrofitVersion: String by rootProject.extra
    val moshiVersion: String by rootProject.extra
    val chuckerVersion: String by rootProject.extra
    val leakCanaryVersion: String by rootProject.extra
    val vicoVersion: String by rootProject.extra
    val junitVersion: String by rootProject.extra
    val junitExtVersion: String by rootProject.extra
    val espressoVersion: String by rootProject.extra
    
    // Android support
    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation("androidx.core:core-ktx:$coreVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    
    // Desugar - Java 8+ APIs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugarJdkLibsVersion")
    
    // Compose - UI toolkit
    implementation(platform("androidx.compose:compose-bom:$composeVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    
    // Navigation - in-app navigation
    implementation("androidx.navigation:navigation-compose:$navigationVersion")
    
    // Hilt - dependency injection
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion")
    
    // Preferences DataStore - persistent storage
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")
    
    // Room - persistent storage
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    
    // Retrofit - REST client
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    
    // Moshi - JSON parser
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    
    // Chucker - HTTP inspector
    debugImplementation("com.github.chuckerteam.chucker:library:$chuckerVersion")
    alphaImplementation("com.github.chuckerteam.chucker:library-no-op:$chuckerVersion")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:$chuckerVersion")
    
    // LeakCanary - memory leak detection
    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")
    
    // Vico - charting
    implementation("com.patrykandpatrick.vico:compose-m3:$vicoVersion")
    
    // Local testing
    testImplementation("junit:junit:$junitVersion")
    
    // Instrumented testing
    androidTestImplementation("androidx.test.ext:junit:$junitExtVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeVersion"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    
}

fun DependencyHandler.alphaImplementation(dependencyNotation: Any): Dependency? {
    return add(
        "alphaImplementation",
        dependencyNotation,
    )
}