plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.xiefrish2021"
version = "3.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.jetbrains:annotations:26.0.1")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

tasks.withType<Jar>() {
    destinationDirectory = layout.buildDirectory.dir("targets")
}

tasks.shadowJar {
    val jarFileName = "${archiveBaseName.get()}-${archiveVersion.get()}"

    archiveFileName = "$jarFileName.jar"
    delete(layout.buildDirectory.file("targets/$jarFileName-all.jar"))
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
