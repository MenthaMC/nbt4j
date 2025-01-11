allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }
}

tasks.getByName("clean") {
    for (project in project.allprojects) {
        val projectDir = project.projectDir

        delete(File(projectDir, ".kotlin"))
        delete(File(projectDir, "target"))
        delete(File(projectDir, "build"))
    }
}
