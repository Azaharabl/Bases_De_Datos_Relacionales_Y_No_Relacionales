import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
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
    //JDBC + SQLITE
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    //RESULT
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")


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