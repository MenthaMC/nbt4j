plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.xiefrish2021"
version = "3.2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.1")
}

//tasks.test {
//    useJUnitPlatform()
//}

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

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        repositories {
            maven("https://maven.pkg.github.com/XieFrish2021/NBT") {
                name = "GitHubPackages"
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "io.github.xiefrish2021"
            artifactId = project.name.lowercase()
            version = project.version.toString()

            from(components["java"])

            pom {
                name = project.name
                description = "A NBT parser library."
                url = "https://www.github.com/XieFrish2021"
                licenses {
                    license {
                        name = "MIT LICENSE"
                        url = "https://raw.githubusercontent.com/XieFrish2021/NBT/refs/heads/master/LICENSE"
                    }
                }

                developers {
                    developer {
                        id = "Frish2021"
                        name = "Xu Zhixuan"
                        email = "1573880184@qq.com"
                    }
                }

                scm {
                    connection = "scm:git:git@github.com:XieFrish2021/NBT.git"
                    developerConnection = "scm:git:ssh://github.com/XieFrish2021/NBT.git"
                    url = "https://github.com/XieFrish2021/NBT"
                }
            }
        }
    }
}
