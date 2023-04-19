import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
    // SQLdelight
    id("com.squareup.sqldelight") version "1.5.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //MOSHI para JSON
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    //PARA USAR NOSHI EN KOTLIN
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")
    //RESULT
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")
    //CACHE4K
    implementation("io.github.reactivecircus.cache4k:cache4k:0.9.0")

    //SQLdelight
    // SqlDeLight, lo mejor es con SQLite para hacer las cosas reactivas o con corrutinas
    implementation("com.squareup.sqldelight:runtime:1.5.4")
    // SQLite para SqlDeLight
    implementation("com.squareup.sqldelight:sqlite-driver:1.5.4")
    // Para poder usar corrutias en SqlDeLight y conectarnos a la base de datos para cambios
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.4")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
sqldelight {
    // Debemos colocarlo en el main/sqldelight/database/AppDatabase.sq
    database("AppDatabase") {
        packageName = "database" // Este es el paquete donde se genera el código en sqldelight
    }
}