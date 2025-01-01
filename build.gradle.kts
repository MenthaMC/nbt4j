plugins {
    java
    `maven-publish`
}

group = "me.coderfrish"
version = "4.2.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>() {
    destinationDirectory = layout.buildDirectory.dir("targets")
}

publishing {
    repositories {
        repositories {
            maven("https://repo.menthamc.com/repository/maven-releases/") {
                name = "MenthaMC"
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name.lowercase()
            version = project.version.toString()

            from(components["java"])

            pom {
                name = project.name
                description = "A NBT parser library."
                url = "https://www.github.com/CoderFrish"
                licenses {
                    license {
                        name = "MIT LICENSE"
                        url = "https://raw.githubusercontent.com/CoderFrish/NBT/refs/heads/master/LICENSE"
                    }
                }

                developers {
                    developer {
                        id = "Frish2021"
                        name = "Xu Zhixuan"
                        email = "1573880184@qq.com"
                        url = "https://github.com/CoderFrish"
                        organization {
                            name = "MenthaMC"
                            url = "https://www.menthamc.com"
                        }
                    }
                }

                contributors {
                    contributor {
                        name = "Kercute"
                        email = "A3167717663@hotmail.com"
                        url = "https://www.github.com/Kercute"
                        organization {
                            name = "MenthaMC"
                            url = "https://www.menthamc.com"
                        }
                    }
                }

                scm {
                    connection = "scm:git:git@github.com:CoderFrish/NBT.git"
                    developerConnection = "scm:git:ssh://github.com/CoderFrish/NBT.git"
                    url = "https://github.com/CoderFrish/NBT"
                }
            }
        }
    }
}
