plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

group = "io.github.raemerrr"
version = project.findProperty("version") as String? ?: "0.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Amount Formatter")
                description.set("A simple library to format amounts")
                url.set("https://github.com/Raemerrr/amount-formatter")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("Raemerrr")
                        name.set("Raemin Kang")
                        email.set("foals18@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Raemerrr/amount-formatter.git")
                    developerConnection.set("scm:git:ssh://git@github.com:Raemerrr/amount-formatter.git")
                    url.set("https://github.com/Raemerrr/amount-formatter")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

signing {
    val signingKey: String? = project.findProperty("gpgPrivateKey") as String? ?: System.getenv("GPG_PRIVATE_KEY")
    val signingPassword: String? = project.findProperty("gpgPassphrase") as String? ?: System.getenv("GPG_PASSPHRASE")

    if (signingKey.isNullOrBlank() || signingPassword.isNullOrBlank()) {
        logger.error("GPG_PRIVATE_KEY or GPG_PASSPHRASE is not set.")
    } else {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}

tasks.test {
    useJUnitPlatform()
}
