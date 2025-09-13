subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = Charsets.UTF_8.name()
            options.release = 21
            options.isFork = true
        }

        withType<Javadoc>().configureEach {
            options.encoding = Charsets.UTF_8.name()
        }

        withType<Javadoc> {
            options {
                (this as StandardJavadocDocletOptions).apply {
                    addStringOption("-add-modules", "jdk.incubator.vector")
                    addStringOption("Xdoclint:none", "-quiet")
                }
            }
        }
    }
}
