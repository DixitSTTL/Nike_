// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false

}
ext {
    val daggerVersion = "2.26"
    val lifeCycleVersion = "2.2.0"
    val okHttpVersion = "3.12.2"
    val lombokVersion = "1.18.22"
    val dotIndicatorVersion = "4.2"
    val rxjava2Version = "2.2.10"
    val rxandroidVersion = "2.1.1"
    val googlePlayAuthVersion = "20.4.1"
    val googlePlayAuthPhoneVersion = "18.0.1"

}