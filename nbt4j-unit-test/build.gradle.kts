val libs = rootProject.libs

dependencies {
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    implementation(project(":nbt4j-core"))
    testImplementation(libs.junitperf)
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}
