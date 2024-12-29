plugins {
    java
    `maven-publish`
}

group = "io.github.xiefrish2021"
version = "3.4.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.1")
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
