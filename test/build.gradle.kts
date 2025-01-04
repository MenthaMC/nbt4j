plugins {
    kotlin("jvm") version "2.0.0"
}

dependencies {
    testImplementation(project(":main"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    testImplementation("org.openjdk.jmh:jmh-core:1.33")
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.33")
}

tasks.test {
    useJUnitPlatform()
}
