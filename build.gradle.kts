// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
    //alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}