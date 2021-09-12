plugins {
    kotlin("jvm")
    java
    kotlin("plugin.serialization") version "1.5.30"
}

group = "com.peterfarlow"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}