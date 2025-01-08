allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }
}

tasks.getByName("clean") {
    delete(
        File(rootDir, ".kotlin"),
        File(rootDir, "target")
    )
}
