plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.apollo)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "dev.ymuratov.core.network"
    compileSdk {
        version = release(36)
    }
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
    apollo {
        service("service") {
            packageName.set("dev.ymuratov.core.network")
            introspection {
                endpointUrl.set("https://rickandmortyapi.com/graphql")
                schemaFile.set(file("src/main/graphql/dev/ymuratov/core/network/schema.graphqls"))
            }
        }
    }
}

dependencies {
    //DI and compilation
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)

    //Data
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.okhttp)
    implementation(libs.apollo.runtime)
}