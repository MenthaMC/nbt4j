pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "nbt"
include(":nbt-test", ":nbt-core", ":nbt-visitor")
