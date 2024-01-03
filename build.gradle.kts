// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val gradleVersion = "8.1.2"
    val kotlinVersion = "1.9.10"
    val kspVersion = "1.9.10-1.0.13"
    val secretsGradlePluginVersion = "2.0.1"
    val hiltVersion = "2.48.1"
    
    id("com.android.application") version gradleVersion apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    id("com.google.devtools.ksp") version kspVersion apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version secretsGradlePluginVersion apply false
    id("com.google.dagger.hilt.android") version hiltVersion apply false
}

val activityVersion by extra { "1.8.2" }
val coreVersion by extra { "1.12.0" }
val lifecycleVersion by extra { "2.6.2" }

val desugarJdkLibsVersion by extra { "2.0.3" }

val composeVersion by extra { "2023.10.01" }
val composeCompilerVersion by extra { "1.5.3" }

val navigationVersion by extra { "2.7.6" }

val hiltVersion by extra { "2.48.1" }
val hiltNavigationComposeVersion by extra { "1.1.0" }

val dataStoreVersion by extra { "1.0.0" }
val roomVersion by extra { "2.6.1" }

val retrofitVersion by extra { "2.9.0" }
val moshiVersion by extra { "1.15.0" }
val chuckerVersion by extra { "4.0.0" }

val accompanistPermissionsVersion by extra { "0.33.2-alpha" }
val leakCanaryVersion by extra { "2.12" }

val vicoVersion by extra { "1.12.0" }

val junitVersion by extra { "4.13.2" }

val junitExtVersion by extra { "1.1.5" }
val espressoVersion by extra { "3.5.1" }